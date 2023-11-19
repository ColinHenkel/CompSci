#include "spimcore.h"

/* ALU */
/* 10 Points */
void ALU(unsigned A,unsigned B,char ALUControl,unsigned *ALUresult,char *Zero)
{

}

/* instruction fetch */
/* 10 Points */
int instruction_fetch(unsigned PC,unsigned *Mem,unsigned *instruction)
{

}

/* instruction partition */
/* 10 Points */
void instruction_partition(unsigned instruction, unsigned *op, unsigned *r1,unsigned *r2, unsigned *r3, unsigned *funct, unsigned *offset, unsigned *jsec)
{

}

/* instruction decode */
/* 15 Points */
int instruction_decode(unsigned op,struct_controls *controls)
{

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
