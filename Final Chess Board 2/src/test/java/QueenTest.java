import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class QueenTest {

	@Test
	public void testWhiteQueenMoves() {
		char[][] board = new char[8][8];
		String whitePiecesInput = "Qa1,Pc3,Bc4,Ke4,Pf2,Rh2,Nh4";
		String blackPiecesInput = "Rc8,Qe8,Pa7,Nb6,Kd6,Bf6,Pg6";

		ChessBoard.placePiecesOnBoard(board, whitePiecesInput, true);
		ChessBoard.placePiecesOnBoard(board, blackPiecesInput, false);
		List<String> validMoves = ChessPieceValidator.validateQueenMoves(board, 0, 0, true);
		System.out.println("White Queen Moves : " + validMoves);
		Set<String> expectedMoves = new HashSet<>();
		expectedMoves.add("b1");
		expectedMoves.add("c1");
		expectedMoves.add("d1");
		expectedMoves.add("e1");
		expectedMoves.add("f1");
		expectedMoves.add("g1");
		expectedMoves.add("h1");
		expectedMoves.add("a2");
		expectedMoves.add("a3");
		expectedMoves.add("a4");
		expectedMoves.add("a5");
		expectedMoves.add("a6");
		expectedMoves.add("b2");
		expectedMoves.add("a7");

		Set<String> actualMoves = new HashSet<>(validMoves);
		assertTrue(actualMoves.equals(expectedMoves));
	}

	@Test
	public void testBlackQueenMoves() {
		char[][] board = new char[8][8];
		String whitePiecesInput = "Qa1,Pc3,Bc4,Ke4,Pf2,Rh2,Nh4";
		String blackPiecesInput = "Rc8,Qe8,Pa7,Nb6,Kd6,Bf6,Pg6";

		ChessBoard.placePiecesOnBoard(board, whitePiecesInput, true);
		ChessBoard.placePiecesOnBoard(board, blackPiecesInput, false);
		List<String> validMoves = ChessPieceValidator.validateQueenMoves(board, 7, 4, false);
		System.out.println("Black Queen Moves : " + validMoves);
		Set<String> expectedMoves = new HashSet<>();
		expectedMoves.add("d8");
		expectedMoves.add("f8");
		expectedMoves.add("g8");
		expectedMoves.add("h8");
		expectedMoves.add("f7");
		expectedMoves.add("e7");
		expectedMoves.add("e6");
		expectedMoves.add("d7");
		expectedMoves.add("c6");
		expectedMoves.add("b5");
		expectedMoves.add("a4");
		expectedMoves.add("e5");
		expectedMoves.add("e4");

		Set<String> actualMoves = new HashSet<>(validMoves);
		assertTrue(actualMoves.equals(expectedMoves));
	}
}