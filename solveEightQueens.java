package eight_queens;

import java.util.ArrayList;
import java.util.Collections;

public class solveEightQueens {
	private ArrayList<Integer> board;
	
	private static class Cell{
		public int row;
		public int col;
		
		public Cell(int r, int c) {
			row = r;
			col = c;
		}
	}
	
	public solveEightQueens() {
		board = new ArrayList<Integer>(Collections.nCopies(8, -1));
	}
	
	private boolean placedEightQueens() {
		if (!board.contains(-1)) return true;
		else return false;
	}
	
	private ArrayList<Cell> getFreeCells(){
		ArrayList<Cell> result = new ArrayList<Cell>();
		
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				Cell candidate = new Cell(i,j);
				
				boolean safe = true;
				
				for (int c=0; c<8;c++) {
					Cell queenPos=new Cell(board.get(c),c);
					if (queenPos.row==-1) continue;
					
					if (j==queenPos.col || i==queenPos.row || (Math.abs(i-queenPos.row) == Math.abs(j-queenPos.col))) {
						safe=false;
						break;
					}
				}
				
				if (safe) result.add(candidate);
			}
		}
		
		return result;
	}
	
	public ArrayList<Integer> getSolution(){
		if (placedEightQueens()) return board;
		
		ArrayList<Cell> freeCells = getFreeCells();
		for (Cell cell: freeCells) {
			board.set(cell.col, cell.row);
			ArrayList<Integer> result = getSolution();	
			if (result != null) return result;
			board.set(cell.col, -1);
		}
		
		return null;		
	}
}
