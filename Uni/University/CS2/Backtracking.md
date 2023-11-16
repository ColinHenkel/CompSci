Backtracking:
	- backtracking is a technique used to solve problems with a large search space by systematically testing and eliminating possibilities
	- a standard example of backtracking is navigating through a maze
	Rat In A Maze:
		> if destination is reached, print matrix
		> else
			a) mark current cell in solution matrix as 1
			b) move down and recursively check if this move leads to a solution
			c) if the move chosen in the above step doesn't lead to a solution then move forward and check if this move leads to a solution
			d) if none of the above solutions work, unmark this cell as 0 (backtrack) and return false
	Eight Queens Problem:
		> find an arrangement of eight queens on a single chess board such that no two queens are attacking one another
		> due to how a queen can move, it is clear that each row and column will have exactly one queen
		> this is also called N queens problem since it can be solved for any NxN board with N queens
		> Backtracking Strategy:
			1) place a queen on the first available square in row 1
			2) move onto the next row, placing a queen on the first available square there (that doesn't conflict with the previously placed queens)
			3) continue in this fashion until either
				a) you have solved the problem or
				b) you get stuck
					=> when you get stuck, remove the queens that got you there until you get to a row where there is another valid square to try
		> when backtracking is carried out, an easy way to visualize what is happening is a tree that shows all the different possibilities that have been tried