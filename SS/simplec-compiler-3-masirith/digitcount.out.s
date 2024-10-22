.file "stdin"
.text
.globl digitminus
.type digitminus, @function
digitminus:
	# emit the function prologue
	push	%rbp
	mov	%rsp, %rbp
	sub	$16, %rsp
	push	%rbx
	# move parameter onto the stack
	mov	%rdi, -8(%rbp)
	# generate code for the body
	# generate code for the right-hand side of the assignment
	# generate code for the left operand
	mov	-8(%rbp), %rax
	push	%rax
	# generate code for the right operand
	# push the integer
	mov	$100, %rax
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
	mov	%rax, -16(%rbp)
	# generate code for the return expression
	mov	-16(%rbp), %rax
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit the epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
.text
.globl digitadd
.type digitadd, @function
digitadd:
	# emit the function prologue
	push	%rbp
	mov	%rsp, %rbp
	sub	$16, %rsp
	push	%rbx
	# move parameter onto the stack
	mov	%rdi, -8(%rbp)
	# generate code for the body
	# generate code for the right-hand side of the assignment
	# generate code for the left operand
	mov	-8(%rbp), %rax
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
	mov	%rax, -16(%rbp)
	# generate code for the return expression
	mov	-16(%rbp), %rax
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit the epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
.text
.globl digitmult
.type digitmult, @function
digitmult:
	# emit the function prologue
	push	%rbp
	mov	%rsp, %rbp
	sub	$16, %rsp
	push	%rbx
	# move parameter onto the stack
	mov	%rdi, -8(%rbp)
	# generate code for the body
	# generate code for the right-hand side of the assignment
	# generate code for the left operand
	mov	-8(%rbp), %rax
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
	pop	%rax
	mov	%rax, -16(%rbp)
	# generate code for the return expression
	mov	-16(%rbp), %rax
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit the epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
.text
.globl digitdiv
.type digitdiv, @function
digitdiv:
	# emit the function prologue
	push	%rbp
	mov	%rsp, %rbp
	sub	$16, %rsp
	push	%rbx
	# move parameter onto the stack
	mov	%rdi, -8(%rbp)
	# generate code for the body
	# generate code for the right-hand side of the assignment
	# generate code for the left operand
	mov	-8(%rbp), %rax
	push	%rax
	# generate code for the right operand
	# push the integer
	mov	$100, %rax
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
	# generate code for the return expression
	mov	-16(%rbp), %rax
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit the epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
.text
.globl digitmod
.type digitmod, @function
digitmod:
	# emit the function prologue
	push	%rbp
	mov	%rsp, %rbp
	sub	$16, %rsp
	push	%rbx
	# move parameter onto the stack
	mov	%rdi, -8(%rbp)
	# generate code for the body
	# generate code for the right-hand side of the assignment
	# generate code for the left operand
	mov	-8(%rbp), %rax
	push	%rax
	# generate code for the right operand
	# push the integer
	mov	$100, %rax
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
	mov	%rax, -16(%rbp)
	# generate code for the return expression
	mov	-16(%rbp), %rax
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit the epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
.text
.globl digitall
.type digitall, @function
digitall:
	# emit the function prologue
	push	%rbp
	mov	%rsp, %rbp
	sub	$16, %rsp
	push	%rbx
	# move parameter onto the stack
	mov	%rdi, -8(%rbp)
	# generate code for the body
	# generate code for the right-hand side of the assignment
	# generate code for the left operand
	# generate code for the left operand
	# generate code for the left operand
	# generate code for the left operand
	# generate code for the left operand
	mov	-8(%rbp), %rax
	push	%rax
	# generate code for the right operand
	# push the integer
	mov	$234, %rax
	push	%rax
	# pop the right operand
	pop	%rbx
	# pop the left operand
	pop	%rax
	# do the subtraction
	sub	%rbx, %rax
	# push the expression result
	push	%rax
	# generate code for the right operand
	# generate code for the left operand
	mov	-8(%rbp), %rax
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
	# pop the right operand
	pop	%rbx
	# pop the left operand
	pop	%rax
	# do the addition
	add	%rbx, %rax
	# push the expression result
	push	%rax
	# generate code for the right operand
	# generate code for the left operand
	mov	-8(%rbp), %rax
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
	# pop the right operand
	pop	%rbx
	# pop the left operand
	pop	%rax
	# do the addition
	add	%rbx, %rax
	# push the expression result
	push	%rax
	# generate code for the right operand
	# generate code for the left operand
	mov	-8(%rbp), %rax
	push	%rax
	# generate code for the right operand
	# push the integer
	mov	$100, %rax
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
	# pop the right operand
	pop	%rbx
	# pop the left operand
	pop	%rax
	# do the addition
	add	%rbx, %rax
	# push the expression result
	push	%rax
	# generate code for the right operand
	# generate code for the left operand
	mov	-8(%rbp), %rax
	push	%rax
	# generate code for the right operand
	# push the integer
	mov	$1, %rax
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
	# pop the right operand
	pop	%rbx
	# pop the left operand
	pop	%rax
	# do the addition
	add	%rbx, %rax
	# push the expression result
	push	%rax
	pop	%rax
	mov	%rax, -16(%rbp)
	# generate code for the return expression
	mov	-16(%rbp), %rax
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
	sub	$24, %rsp
	push	%rbx
	# move argc and argv from parameter registers to the stack
	mov	%rdi, -16(%rbp)
	mov	%rsi, -24(%rbp)
	# generate code for the body
	# generate code for the right-hand side of the assignment
	# push the integer
	mov	$1234, %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	digitminus
	# push the return value
	push	%rax
	pop	%rax
	mov	%rax, -8(%rbp)
	# generate code for the right-hand side of the assignment
	# push the integer
	mov	$1234, %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	digitadd
	# push the return value
	push	%rax
	pop	%rax
	mov	%rax, -8(%rbp)
	# generate code for the right-hand side of the assignment
	# push the integer
	mov	$1234, %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	digitmult
	# push the return value
	push	%rax
	pop	%rax
	mov	%rax, -8(%rbp)
	# generate code for the right-hand side of the assignment
	# push the integer
	mov	$1234, %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	digitmod
	# push the return value
	push	%rax
	pop	%rax
	mov	%rax, -8(%rbp)
	# generate code for the right-hand side of the assignment
	# push the integer
	mov	$1234, %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	digitall
	# push the return value
	push	%rax
	pop	%rax
	mov	%rax, -8(%rbp)
	# generate code for the return expression
	# push the integer
	mov	$0, %rax
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit main's epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
