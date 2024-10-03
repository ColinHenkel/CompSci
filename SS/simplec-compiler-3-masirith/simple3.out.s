.file "stdin"
.text
.globl main
.type main, @function
main:
	# stack space for argc and argv
	# emit main's prologue
	push	%rbp
	mov	%rsp, %rbp
	sub	$16, %rsp
	push	%rbx
	# move argc and argv from parameter registers to the stack
	mov	%rdi, -8(%rbp)
	mov	%rsi, -16(%rbp)
	# generate code for the body
	# generate code for the return expression
	# generate code for the left operand
	# push the integer
	mov	$2, %rax
	push	%rax
	# generate code for the right operand
	# push the integer
	mov	$3, %rax
	push	%rax
	# pop the right operand
	pop	%rbx
	# pop the left operand
	pop	%rax
	# do the addition
	add	%rbx, %rax
	# push the expression result
	push	%rax
	# save the return expression into %rax per the abi
	pop	%rax
	# emit main's epilogue
	pop	%rbx
	mov	%rbp, %rsp
	pop	%rbp
	ret
