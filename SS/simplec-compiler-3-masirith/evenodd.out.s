.file "stdin"
.text
.globl isodd
.type isodd, @function
isodd:
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
	mov	$1, %rax
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit the epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
.text
.globl iseven
.type iseven, @function
iseven:
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
	mov	$2, %rax
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
	sub	$40, %rsp
	push	%rbx
	# move argc and argv from parameter registers to the stack
	mov	%rdi, -32(%rbp)
	mov	%rsi, -40(%rbp)
	# generate code for the body
	# generate code for the right-hand side of the assignment
	# push the integer
	mov	$3, %rax
	push	%rax
	pop	%rax
	mov	%rax, -8(%rbp)
	# generate code for the right-hand side of the assignment
	# push the integer
	mov	$2, %rax
	push	%rax
	pop	%rax
	mov	%rax, -16(%rbp)
	# generate code for the right-hand side of the assignment
	mov	-8(%rbp), %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	isodd
	# push the return value
	push	%rax
	pop	%rax
	mov	%rax, -24(%rbp)
	# generate code for the right-hand side of the assignment
	mov	-16(%rbp), %rax
	push	%rax
	# pass the parameter
	pop	%rdi
	# call the function
	call	iseven
	# push the return value
	push	%rax
	pop	%rax
	mov	%rax, -24(%rbp)
	# generate code for the return expression
	# push the integer
	mov	$3, %rax
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit main's epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
