package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;
import edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess.Piece.PieceName;
import edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess.Piece.Side;

import java.io.Serializable;

public class Rule implements Serializable {
  Board board = null;
  Piece piece = null;
  Position pos[][];
  int starti,endi,startj,endj;
  public Rule(Board board, Position pos[][]){
    this.board = board;
    this.pos = pos;
  }

  public boolean moveJudge(Piece piece, int starti, int startj, int endi, int endj){
    this.piece = piece;
    this.starti = starti;
    this.endi = endi;
    this.startj = startj;
    this.endj = endj;
    int min_i = Math.min(starti,endi);
    int max_i = Math.max(starti,endi);
    int min_j = Math.min(startj,endj);
    int max_j = Math.max(startj,endj);
    int i = 0;
    boolean move = false;
    //System.out.println(piece.getName() );
    if (piece.getName().equals(PieceName.Chariot.toString())) {
      if (starti == endi) {
        for (i = min_j+1; i <= max_j-1; i++) {
          if (pos[starti][i].hasPiece()) {
            System.out.println("Rule Break: 1"); move = false;
            break;
          }
        } if (i == max_j) {
          move = true;
        }
      } else if (startj == endj) {
        for (i = min_i+1; i<=max_i-1; i++) {
          if (pos[i][startj].hasPiece()) {
            System.out.println("Rule Break: 2"); move = false;
            break;
          }
        } if (i == max_i) {
          move = true;
        }
      } else {
        System.out.println("Rule Break: 3"); move = false;
      }
    } else if (piece.getName().equals(PieceName.Knight.toString())) {
      int x = Math.abs(starti - endi);
      int y = Math.abs(startj - endj);
      if (x == 2 && y == 1) {
        if (endi > starti) {
          if (pos[starti+1][startj].hasPiece()) {
            System.out.println("Rule Break: 4"); move = false;
          } else {
            move = true;
          }
        } if (endi < starti) {
          if (pos[starti-1][startj].hasPiece()) {
            System.out.println("Rule Break: 5"); move = false;
          } else {
            move = true;
          }
        }
      } else if (x == 1 && y == 2) {
        if (endj > startj) {
          if (pos[starti][startj+1].hasPiece()) {
            System.out.println("Rule Break: 6"); move = false;
          } else {
            move = true;
          }
        } if (endj < startj) {
          if (pos[starti][startj-1].hasPiece()) {
            System.out.println("Rule Break: 7"); move = false;
          } else {
            move = true;
          }
        }
      } else {
        System.out.println("Rule Break: 8"); move = false;
      }
    } else if (piece.getName().equals(PieceName.Bishop.toString()) && piece.getSide().equals(Side.Chu)) {  // xiang chu
      int mid_i = (starti + endi) / 2;
      int mid_j = (startj + endj) / 2;
      int x = Math.abs(starti - endi);
      int y = Math.abs(startj - endj);
      if (x == 2 && y == 2 && endj <= 5) {
        if (pos[mid_i][mid_j].hasPiece()) {
          System.out.println("Rule Break: 9"); move = false;
        } else {
          move = true;
        }
      } else {
    	  System.out.println("Rule Break: 21");move = false;
      }
    } else if (piece.getName().equals(PieceName.Bishop.toString()) && piece.getSide().equals(Side.Han)) {  // xiang han(hong
      int mid_i = (starti + endi) / 2;
      int mid_j = (startj + endj) / 2;
      int x = Math.abs(starti - endi);
      int y = Math.abs(startj - endj);
      if (x == 2 && y == 2 && endj >= 6) {
        if (pos[mid_i][mid_j].hasPiece()) {
          System.out.println("Rule Break: 10"); move = false;
        } else {
          move = true;
        }
      } else {
    	  System.out.println("Rule Break: 22");move =  false;
      }
    }else if (piece.getName().equals(PieceName.Cannon.toString())) {
      int count = 0;
      if (starti == endi) {
        for (i = min_j+1; i <= max_j-1; i++) {
          if (pos[starti][i].hasPiece()) {
            count++;
          }
        } if (count > 1) {
          System.out.println("Rule Break: 11"); move = false;
        } else if (count == 1) {
          if (pos[endi][endj].hasPiece()) {
            move = true;
          }
        }
        else if (count == 0 && !pos[endi][endj].hasPiece()) {
  	      move = true;
  	  	}
	  } else if (startj == endj) {
	    for (i = min_i+1; i<=max_i-1; i++) {
	      if (pos[i][startj].hasPiece()) {
	        count++;
	      }
	    } if (count > 1) {
	      System.out.println("Rule Break: 12"); move = false;
	    } else if (count == 1) {
	      if (pos[endi][endj].hasPiece()) {
	        move = true;
	      }
	    } else if (count == 0 && !pos[endi][endj].hasPiece()) {
	      move = true;
	    }
      } else {
       System.out.println("Rule Break: 13"); move = false;
      }
    } else if (piece.getName().equals(PieceName.Pawn.toString()) && piece.getSide().equals(Side.Han)) {
      int x = Math.abs(starti - endi);
      int y = Math.abs(startj - endj);
      if (endj >= 6) {
        if (startj - endj == 1 && x == 0) {
          move = true;
        } else {
          System.out.println("Rule Break: 14"); move = false;
        }
      } else if (endj <= 5) {
        if (startj - endj == 1 && x == 0) {
          move = true;
        } else if (endj - startj == 0 && x == 1) {
          move = true;
        } else {
          System.out.println("Rule Break: 15"); move = false;
        }
      }
    } else if (piece.getName().equals(PieceName.Pawn.toString()) && piece.getSide().equals(Side.Chu)) {
      int x = Math.abs(starti - endi);
      int y = Math.abs(startj - endj);
      if (endj <= 5) {
        if (endj - startj == 1 && x == 0) {
          move = true;
        } else {
          System.out.println("Rule Break: 16"); move = false;
        }
      } else if (endj >= 6) {
        if (endj - startj == 1 && x == 0) {
          move = true;
        } else if (endj- startj == 0 && x == 1) {
          move = true;
        } else {
          System.out.println("Rule Break: 17"); move = false;
        }
      }
    } else if (piece.getName().equals(PieceName.Advisor.toString())) {
      int x = Math.abs(starti - endi);
      int y = Math.abs(startj - endj);
      if (endi >= 4 && endi <= 6 && x == 1 && y == 1) {
        move = true;
      } else {
        System.out.println("Rule Break: 18"); move = false;
      }
    } else if (piece.getName().equals(PieceName.General.toString())) {
      int x = Math.abs(starti - endi);
      int y = Math.abs(startj - endj);
      if (endi >=4 && endi <= 6) {
        if ((x == 1 && y == 0) || (x == 0 && y== 1)) {
          move = true;
        } else {
          System.out.println("Rule Break: 19"); move = false;
        }
      } else {
        System.out.println("Rule Break: 20"); move = false;
      }
    }
    return move;
  }
}
