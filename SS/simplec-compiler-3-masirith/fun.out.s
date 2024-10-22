.file "stdin"
.text
.globl f
.type f, @function
f:
	# emit the function prologue
	push	%rbp
	mov	%rsp, %rbp
	sub	$8, %rsp
	push	%rbx
	# move parameter onto the stack
	mov	%rdi, -8(%rbp)
	# generate code for the body
	# generate code for the return expression
	# push the integer
	mov	$5, %rax
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit the epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
.text
.globl fu
.type fu, @function
fu:
	# emit the function prologue
	push	%rbp
	mov	%rsp, %rbp
	sub	$8, %rsp
	push	%rbx
	# move parameter onto the stack
	mov	%rdi, -8(%rbp)
	# generate code for the body
	# generate code for the return expression
	# push the integer
	mov	$5, %rax
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit the epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
.text
.globl fun
.type fun, @function
fun:
	# emit the function prologue
	push	%rbp
	mov	%rsp, %rbp
	sub	$8, %rsp
	push	%rbx
	# move parameter onto the stack
	mov	%rdi, -8(%rbp)
	# generate code for the body
	# generate code for the return expression
	# push the integer
	mov	$5, %rax
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit the epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
.text
.globl func
.type func, @function
func:
	# emit the function prologue
	push	%rbp
	mov	%rsp, %rbp
	sub	$8, %rsp
	push	%rbx
	# move parameter onto the stack
	mov	%rdi, -8(%rbp)
	# generate code for the body
	# generate code for the return expression
	# push the integer
	mov	$5, %rax
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit the epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
.text
.globl funct
.type funct, @function
funct:
	# emit the function prologue
	push	%rbp
	mov	%rsp, %rbp
	sub	$8, %rsp
	push	%rbx
	# move parameter onto the stack
	mov	%rdi, -8(%rbp)
	# generate code for the body
	# generate code for the return expression
	# push the integer
	mov	$5, %rax
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit the epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
.text
.globl functi
.type functi, @function
functi:
	# emit the function prologue
	push	%rbp
	mov	%rsp, %rbp
	sub	$8, %rsp
	push	%rbx
	# move parameter onto the stack
	mov	%rdi, -8(%rbp)
	# generate code for the body
	# generate code for the return expression
	# push the integer
	mov	$5, %rax
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit the epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
.text
.globl functio
.type functio, @function
functio:
	# emit the function prologue
	push	%rbp
	mov	%rsp, %rbp
	sub	$8, %rsp
	push	%rbx
	# move parameter onto the stack
	mov	%rdi, -8(%rbp)
	# generate code for the body
	# generate code for the return expression
	# push the integer
	mov	$5, %rax
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit the epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
.text
.globl functionnn
.type functionnn, @function
functionnn:
	# emit the function prologue
	push	%rbp
	mov	%rsp, %rbp
	sub	$8, %rsp
	push	%rbx
	# move parameter onto the stack
	mov	%rdi, -8(%rbp)
	# generate code for the body
	# generate code for the return expression
	# push the integer
	mov	$5, %rax
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit the epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
.text
.globl main
.type main, @function
main:
	# stack space for argc and argv
	# emit main's prologue
	push	%rbp
	mov	%rsp, %rbp
	sub	$48, %rsp
	push	%rbx
	# move argc and argv from parameter registers to the stack
	mov	%rdi, -40(%rbp)
	mov	%rsi, -48(%rbp)
	# generate code for the body
	# generate code for the right-hand side of the assignment
	# generate code for the left operand
	# push the integer
	mov	$1, %rax
	push	%rax
	# generate code for the right operand
	# push the integer
	mov	$1, %rax
	push	%rax
	# pop the right operand
	pop	%rbx
	# pop the left operand
	pop	%rax
	# do the addition
	add	%rbx, %rax
	# push the expression result
	push	%rax
	pop	%rax
	mov	%rax, -24(%rbp)
	# generate code for the right-hand side of the assignment
	# generate code for the left operand
	mov	-24(%rbp), %rax
	push	%rax
	# generate code for the right operand
	# push the integer
	mov	$1, %rax
	push	%rax
	# pop the right operand
	pop	%rbx
	# pop the left operand
	pop	%rax
	# do the subtraction
	sub	%rbx, %rax
	# push the expression result
	push	%rax
	pop	%rax
	mov	%rax, -8(%rbp)
	# generate code for the right-hand side of the assignment
	# generate code for the left operand
	mov	-24(%rbp), %rax
	push	%rax
	# generate code for the right operand
	mov	-8(%rbp), %rax
	push	%rax
	# pop the right operand
	pop	%rbx
	# pop the left operand
	pop	%rax
	# do the multiplication
	imul	%rbx, %rax
	# push the expression result
	push	%rax
	pop	%rax
	mov	%rax, -16(%rbp)
	# generate code for the right-hand side of the assignment
	mov	-16(%rbp), %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	f
	# push the return value
	push	%rax
	pop	%rax
	mov	%rax, -32(%rbp)
	# generate code for the right-hand side of the assignment
	mov	-32(%rbp), %rax
	push	%rax
	pop	%rax
	mov	%rax, -16(%rbp)
	# generate code for the right-hand side of the assignment
	mov	-24(%rbp), %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	fu
	# push the return value
	push	%rax
	pop	%rax
	mov	%rax, -32(%rbp)
	# generate code for the right-hand side of the assignment
	mov	-32(%rbp), %rax
	push	%rax
	pop	%rax
	mov	%rax, -24(%rbp)
	# generate code for the right-hand side of the assignment
	mov	-8(%rbp), %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	fun
	# push the return value
	push	%rax
	pop	%rax
	mov	%rax, -32(%rbp)
	# generate code for the right-hand side of the assignment
	mov	-32(%rbp), %rax
	push	%rax
	pop	%rax
	mov	%rax, -8(%rbp)
	# generate code for the right-hand side of the assignment
	# generate code for the left operand
	# generate code for the left operand
	# generate code for the left operand
	mov	-16(%rbp), %rax
	push	%rax
	# generate code for the right operand
	mov	-24(%rbp), %rax
	push	%rax
	# pop the right operand
	pop	%rbx
	# pop the left operand
	pop	%rax
	# do the division
	cdq
	idiv	%rbx
	# push the expression result
	push	%rax
	# generate code for the right operand
	# push the integer
	mov	$10, %rax
	push	%rax
	# pop the right operand
	pop	%rbx
	# pop the left operand
	pop	%rax
	# do the multiplication
	imul	%rbx, %rax
	# push the expression result
	push	%rax
	# generate code for the right operand
	# push the integer
	mov	$10, %rax
	push	%rax
	# pop the right operand
	pop	%rbx
	# pop the left operand
	pop	%rax
	# do the remainder
	cdq
	idiv	%rbx
	mov	%rdx, %rax
	# push the expression result
	push	%rax
	pop	%rax
	mov	%rax, -8(%rbp)
	# generate code for the right-hand side of the assignment
	# generate code for the left operand
	# push the integer
	mov	$1, %rax
	push	%rax
	# generate code for the right operand
	# push the integer
	mov	$1, %rax
	push	%rax
	# pop the right operand
	pop	%rbx
	# pop the left operand
	pop	%rax
	# do the addition
	add	%rbx, %rax
	# push the expression result
	push	%rax
	pop	%rax
	mov	%rax, -24(%rbp)
	# generate code for the right-hand side of the assignment
	# generate code for the left operand
	mov	-24(%rbp), %rax
	push	%rax
	# generate code for the right operand
	# push the integer
	mov	$1, %rax
	push	%rax
	# pop the right operand
	pop	%rbx
	# pop the left operand
	pop	%rax
	# do the subtraction
	sub	%rbx, %rax
	# push the expression result
	push	%rax
	pop	%rax
	mov	%rax, -8(%rbp)
	# generate code for the right-hand side of the assignment
	mov	-16(%rbp), %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	func
	# push the return value
	push	%rax
	pop	%rax
	mov	%rax, -32(%rbp)
	# generate code for the right-hand side of the assignment
	# generate code for the left operand
	mov	-24(%rbp), %rax
	push	%rax
	# generate code for the right operand
	mov	-8(%rbp), %rax
	push	%rax
	# pop the right operand
	pop	%rbx
	# pop the left operand
	pop	%rax
	# do the multiplication
	imul	%rbx, %rax
	# push the expression result
	push	%rax
	pop	%rax
	mov	%rax, -16(%rbp)
	# generate code for the right-hand side of the assignment
	mov	-16(%rbp), %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	funct
	# push the return value
	push	%rax
	pop	%rax
	mov	%rax, -32(%rbp)
	# generate code for the right-hand side of the assignment
	# generate code for the left operand
	# generate code for the left operand
	# generate code for the left operand
	mov	-24(%rbp), %rax
	push	%rax
	# generate code for the right operand
	# push the integer
	mov	$2, %rax
	push	%rax
	# pop the right operand
	pop	%rbx
	# pop the left operand
	pop	%rax
	# do the multiplication
	imul	%rbx, %rax
	# push the expression result
	push	%rax
	# generate code for the right operand
	# push the integer
	mov	$3, %rax
	push	%rax
	# pop the right operand
	pop	%rbx
	# pop the left operand
	pop	%rax
	# do the remainder
	cdq
	idiv	%rbx
	mov	%rdx, %rax
	# push the expression result
	push	%rax
	# generate code for the right operand
	mov	-24(%rbp), %rax
	push	%rax
	# pop the right operand
	pop	%rbx
	# pop the left operand
	pop	%rax
	# do the division
	cdq
	idiv	%rbx
	# push the expression result
	push	%rax
	pop	%rax
	mov	%rax, -16(%rbp)
	# generate code for the right-hand side of the assignment
	# push the integer
	mov	$6, %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	functi
	# push the return value
	push	%rax
	pop	%rax
	mov	%rax, -8(%rbp)
	# generate code for the right-hand side of the assignment
	mov	-32(%rbp), %rax
	push	%rax
	pop	%rax
	mov	%rax, -8(%rbp)
	# generate code for the right-hand side of the assignment
	# push the integer
	mov	$7, %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	functio
	# push the return value
	push	%rax
	pop	%rax
	mov	%rax, -32(%rbp)
	# generate code for the right-hand side of the assignment
	# push the integer
	mov	$8, %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	functionnn
	# push the return value
	push	%rax
	pop	%rax
	mov	%rax, -8(%rbp)
	# generate code for the right-hand side of the assignment
	mov	-16(%rbp), %rax
	push	%rax
	pop	%rax
	mov	%rax, -8(%rbp)
	# generate code for the right-hand side of the assignment
	mov	-24(%rbp), %rax
	push	%rax
	pop	%rax
	mov	%rax, -16(%rbp)
	# generate code for the right-hand side of the assignment
	mov	-8(%rbp), %rax
	push	%rax
	pop	%rax
	mov	%rax, -24(%rbp)
	# generate code for the return expression
	# push the integer
	mov	$42, %rax
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit main's epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
