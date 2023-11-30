#include "spimcore.h"

/* ALU */
/* 10 Points */
void ALU(unsigned A,unsigned B,char ALUControl,unsigned *ALUresult,char *Zero)
{
    if(ALUControl == 0b000) // add
		*ALUresult = A + B;
	else if(ALUControl == 0b001) // sub
		*ALUresult = A - B;
	
	else if(ALUControl == 0b010) { // slt
		int tempA = (int) A;
		int tempB = (int) B;
		
		if(tempA < tempB) 
			*ALUresult = 1;
		else 
			*ALUresult = 0;
	}

	else if(ALUControl == 0b011) { // sltu
		if(A < B)
			*ALUresult = 1;
		else 
			*ALUresult = 0;
    }

	else if(ALUControl == 0b100) // and
		*ALUresult = A & B;
	else if(ALUControl == 0b101) // or
		*ALUresult = A | B;
	else if(ALUControl == 0b110) // B << 16 bits
		*ALUresult = B << 16;
	else if(ALUControl == 0b111) // flip A
		*ALUresult = ~A;
	if(*ALUresult == 0) // set the zero output
		*Zero = 1;
	else 
		*Zero = 0;
	
	return;
}

/* instruction fetch */
/* 10 Points */
int instruction_fetch(unsigned PC,unsigned *Mem,unsigned *instruction)
{
	if(PC % 4 != 0)
        return 1; 	
	
    *instruction = Mem[PC >> 2];
	return 0;
}

/* instruction partition */
/* 10 Points */
void instruction_partition(unsigned instruction, unsigned *op, unsigned *r1,unsigned *r2, unsigned *r3, unsigned *funct, unsigned *offset, unsigned *jsec)
{
	*op     = (instruction & 0xfc000000) >> 26; // 31-26
	*r1     = (instruction & 0x03e00000) >> 21; // 25-21
	*r2     = (instruction & 0x001f0000) >> 16; // 20-16
	*r3     = (instruction & 0x0000f800) >> 11; // 15-11
	*funct  =  instruction & 0x0000003f;
	*offset =  instruction & 0x0000ffff;	
	*jsec   =  instruction & 0x03ffffff;	
}

/* instruction decode */
/* 15 Points */
int instruction_decode(unsigned op,struct_controls *controls)
{
    controls->Jump = 0;
    controls->Branch = 0;
    controls->MemRead = 0;
    controls->MemtoReg = 0;
    controls->MemWrite = 0;
    controls->RegDst = 0;
    controls->RegWrite = 0;
    controls->ALUSrc = 0;
    controls->ALUOp = 0;

    switch(op) {
        case(0): // R-type
            controls->RegDst = 1;
            controls->RegWrite = 1;
            controls->ALUOp = 7;
            break;
        case(2): // j
            controls->Jump = 1;
            break;
        case(4): // beq
            controls->Branch = 1;
            controls->ALUOp = 1;
            break;
        case(8): // addi
            controls->RegWrite = 1;
            controls->ALUSrc = 1;
            break;
        case(10): // slti
            controls->RegWrite = 1;
            controls->ALUSrc = 1;
            controls->ALUOp = 2;
            break;
        case(11): // sltiu
            controls->RegWrite = 1;
            controls->ALUSrc = 1;
            controls->ALUOp = 3;
            break;
        case(15): // lui
            controls->RegWrite = 1;
            controls->ALUSrc = 1;
            controls->ALUOp = 6;
            break;
        case(35): // lw
            controls->MemRead = 1;
            controls->MemtoReg = 1;
            controls->RegWrite = 1;
            controls->ALUSrc = 1;
            break;
        case(43): // sw
            controls->MemWrite = 1;
            controls->ALUSrc = 1;
            break;
        default: // invalid
            return 1;
    }
    return 0;
}

/* Read Register */
/* 5 Points */
void read_register(unsigned r1,unsigned r2,unsigned *Reg,unsigned *data1,unsigned *data2)
{
    *data1 = Reg[r1];
    *data2 = Reg[r2];
}

/* Sign Extend */
/* 10 Points */
void sign_extend(unsigned offset,unsigned *extended_value)
{
    if((offset >> 15) == 1)
        *extended_value = offset | 0xFFFF0000; // perform sign extension for negative values
    else
        *extended_value = offset & 0x0000FFFF; // if not negative copy lower 16 bits
}

/* ALU operations */
/* 10 Points */
int ALU_operations(unsigned data1,unsigned data2,unsigned extended_value,unsigned funct,char ALUOp,char ALUSrc,unsigned *ALUresult,char *Zero)
{

}

/* Read / Write Memory */
/* 10 Points */
int rw_memory(unsigned ALUresult,unsigned data2,char MemWrite,char MemRead,unsigned *memdata,unsigned *Mem)
{
    if(MemRead == 1) {
        if(ALUresult % 4 == 0 && ALUresult < 65536) // check for misalignment or outofbounds access
            *memdata = Mem[ALUresult >> 2];
        else
            return 1; // error code 1
    }

    if(MemWrite == 1) {
        if(ALUresult % 4 == 0 && ALUresult < 65536) // check for misalignment or outofbounds access
            Mem[ALUresult >> 2] = data2;
        else
            return 1; // error code 1
    }

    return 0;
}

/* Write Register */
/* 10 Points */
void write_register(unsigned r2,unsigned r3,unsigned memdata,unsigned ALUresult,char RegWrite,char RegDst,char MemtoReg,unsigned *Reg)
{
    if(RegWrite == 1) {
        if(MemtoReg == 1)
            Reg[r2] = memdata;
        else if(MemtoReg == 0) {    // info to be stored is coming from register
            if(RegDst == 1)
                Reg[r3] = ALUresult;    // regdst is 1 so instruction is r type meaning write to register r3
            else 
                Reg[r2] = ALUresult;    // regdst is 0 so instruction is i type meaning write to register r2
        }
    }
}

/* PC update */
/* 10 Points */
void PC_update(unsigned jsec,unsigned extended_value,char Branch,char Jump,char Zero,unsigned *PC)
{

}
