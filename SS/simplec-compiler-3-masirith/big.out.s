.file "stdin"
.text
.globl a
.type a, @function
a:
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
.globl b
.type b, @function
b:
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
	mov	$10, %rax
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit the epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
.text
.globl c
.type c, @function
c:
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
	mov	$15, %rax
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit the epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
.text
.globl d
.type d, @function
d:
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
	mov	$20, %rax
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit the epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
.text
.globl e
.type e, @function
e:
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
	mov	$25, %rax
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit the epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
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
	mov	$30, %rax
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit the epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
.text
.globl g
.type g, @function
g:
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
	mov	$35, %rax
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
	# push the integer
	mov	$1, %rax
	push	%rax
	pop	%rax
	mov	%rax, -8(%rbp)
	# generate code for the right-hand side of the assignment
	# push the integer
	mov	$1, %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	a
	# push the return value
	push	%rax
	pop	%rax
	mov	%rax, -16(%rbp)
	# generate code for the right-hand side of the assignment
	# generate code for the left operand
	# push the integer
	mov	$2, %rax
	push	%rax
	# generate code for the right operand
	mov	-16(%rbp), %rax
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
	mov	%rax, -32(%rbp)
	# generate code for the right-hand side of the assignment
	# generate code for the left operand
	mov	-16(%rbp), %rax
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
	mov	%rax, -16(%rbp)
	# generate code for the right-hand side of the assignment
	# push the integer
	mov	$1, %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	b
	# push the return value
	push	%rax
	pop	%rax
	mov	%rax, -24(%rbp)
	# generate code for the right-hand side of the assignment
	# generate code for the left operand
	# push the integer
	mov	$2, %rax
	push	%rax
	# generate code for the right operand
	mov	-16(%rbp), %rax
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
	mov	%rax, -32(%rbp)
	# generate code for the right-hand side of the assignment
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
	# do the addition
	add	%rbx, %rax
	# push the expression result
	push	%rax
	pop	%rax
	mov	%rax, -16(%rbp)
	# generate code for the right-hand side of the assignment
	# push the integer
	mov	$1, %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	c
	# push the return value
	push	%rax
	pop	%rax
	mov	%rax, -24(%rbp)
	# generate code for the right-hand side of the assignment
	# generate code for the left operand
	# push the integer
	mov	$2, %rax
	push	%rax
	# generate code for the right operand
	mov	-16(%rbp), %rax
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
	mov	%rax, -32(%rbp)
	# generate code for the right-hand side of the assignment
	# generate code for the left operand
	# generate code for the left operand
	mov	-24(%rbp), %rax
	push	%rax
	# generate code for the right operand
	mov	-24(%rbp), %rax
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
	mov	$1, %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	a
	# push the return value
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
	# generate code for the left operand
	mov	-16(%rbp), %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	d
	# push the return value
	push	%rax
	# generate code for the right operand
	# push the integer
	mov	$1, %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	a
	# push the return value
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
	mov	%rax, -24(%rbp)
	# generate code for the right-hand side of the assignment
	# generate code for the left operand
	# push the integer
	mov	$2, %rax
	push	%rax
	# generate code for the right operand
	mov	-16(%rbp), %rax
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
	mov	%rax, -32(%rbp)
	# generate code for the right-hand side of the assignment
	# generate code for the left operand
	# generate code for the left operand
	# generate code for the left operand
	# generate code for the left operand
	# generate code for the left operand
	# generate code for the left operand
	mov	-16(%rbp), %rax
	push	%rax
	# generate code for the right operand
	mov	-8(%rbp), %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	g
	# push the return value
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
	mov	-16(%rbp), %rax
	push	%rax
	# generate code for the right operand
	mov	-8(%rbp), %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	g
	# push the return value
	push	%rax
	# pop the right operand
	pop	%rbx
	# pop the left operand
	pop	%rax
	# do the subtraction
	sub	%rbx, %rax
	# push the expression result
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
	# generate code for the left operand
	mov	-16(%rbp), %rax
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
	# pop the right operand
	pop	%rbx
	# pop the left operand
	pop	%rax
	# do the multiplication
	imul	%rbx, %rax
	# push the expression result
	push	%rax
	# generate code for the right operand
	# generate code for the left operand
	mov	-16(%rbp), %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	f
	# push the return value
	push	%rax
	# generate code for the right operand
	mov	-16(%rbp), %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	b
	# push the return value
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
	# do the multiplication
	imul	%rbx, %rax
	# push the expression result
	push	%rax
	# generate code for the right operand
	# generate code for the left operand
	mov	-16(%rbp), %rax
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
	# pop the right operand
	pop	%rbx
	# pop the left operand
	pop	%rax
	# do the multiplication
	imul	%rbx, %rax
	# push the expression result
	push	%rax
	# generate code for the right operand
	# generate code for the left operand
	mov	-16(%rbp), %rax
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
	# do the multiplication
	imul	%rbx, %rax
	# push the expression result
	push	%rax
	pop	%rax
	mov	%rax, -24(%rbp)
	# generate code for the right-hand side of the assignment
	# push the integer
	mov	$1, %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	e
	# push the return value
	push	%rax
	pop	%rax
	mov	%rax, -24(%rbp)
	# generate code for the right-hand side of the assignment
	# generate code for the left operand
	# push the integer
	mov	$2, %rax
	push	%rax
	# generate code for the right operand
	mov	-16(%rbp), %rax
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
	mov	%rax, -32(%rbp)
	# generate code for the right-hand side of the assignment
	# generate code for the left operand
	# generate code for the left operand
	# generate code for the left operand
	# generate code for the left operand
	# generate code for the left operand
	# generate code for the left operand
	mov	-16(%rbp), %rax
	push	%rax
	# generate code for the right operand
	mov	-8(%rbp), %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	g
	# push the return value
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
	mov	-16(%rbp), %rax
	push	%rax
	# generate code for the right operand
	mov	-8(%rbp), %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	g
	# push the return value
	push	%rax
	# pop the right operand
	pop	%rbx
	# pop the left operand
	pop	%rax
	# do the subtraction
	sub	%rbx, %rax
	# push the expression result
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
	# generate code for the left operand
	mov	-16(%rbp), %rax
	push	%rax
	# generate code for the right operand
	# push the integer
	mov	$1, %rax
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
	# do the multiplication
	imul	%rbx, %rax
	# push the expression result
	push	%rax
	# generate code for the right operand
	# generate code for the left operand
	mov	-16(%rbp), %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	f
	# push the return value
	push	%rax
	# generate code for the right operand
	mov	-16(%rbp), %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	b
	# push the return value
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
	# do the multiplication
	imul	%rbx, %rax
	# push the expression result
	push	%rax
	# generate code for the right operand
	# generate code for the left operand
	mov	-16(%rbp), %rax
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
	# do the multiplication
	imul	%rbx, %rax
	# push the expression result
	push	%rax
	# generate code for the right operand
	# generate code for the left operand
	# generate code for the left operand
	mov	-16(%rbp), %rax
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
	# generate code for the right operand
	# push the integer
	mov	$2, %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	a
	# push the return value
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
	# do the multiplication
	imul	%rbx, %rax
	# push the expression result
	push	%rax
	pop	%rax
	mov	%rax, -24(%rbp)
	# generate code for the right-hand side of the assignment
	# push the integer
	mov	$29, %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	f
	# push the return value
	push	%rax
	pop	%rax
	mov	%rax, -16(%rbp)
	# generate code for the right-hand side of the assignment
	# push the integer
	mov	$35, %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	g
	# push the return value
	push	%rax
	pop	%rax
	mov	%rax, -24(%rbp)
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
	# do the multiplication
	imul	%rbx, %rax
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
	# generate code for the right operand
	# generate code for the left operand
	mov	-24(%rbp), %rax
	push	%rax
	# generate code for the right operand
	mov	-24(%rbp), %rax
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
	pop	%rax
	mov	%rax, -8(%rbp)
	# generate code for the return expression
	# push the integer
	mov	$1, %rax
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit main's epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
