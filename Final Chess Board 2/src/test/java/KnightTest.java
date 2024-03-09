import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class KnightTest {

	@Test
	public void testWhiteKnightMoves() {
		char[][] board = new char[8][8];
		String whitePiecesInput = "Qa1,Pc3,Bc4,Ke4,Pf2,Rh2,Nh4";
		String blackPiecesInput = "Rc8,Qe8,Pa7,Nb6,Kd6,Bf6,Pg6";

		ChessBoard.placePiecesOnBoard(board, whitePiecesInput, true);
		ChessBoard.placePiecesOnBoard(board, blackPiecesInput, false);
		List<String> validMoves = ChessPieceValidator.validateKnightMoves(board, 3, 7, true);
		System.out.println("White Knight Moves : " + validMoves);
		Set<String> expectedMoves = new HashSet<>();
		expectedMoves.add("f3");
		expectedMoves.add("g2");
		expectedMoves.add("f5");
		expectedMoves.add("g6");

		Set<String> actualMoves = new HashSet<>(validMoves);
		assertTrue(actualMoves.equals(expectedMoves));
	}

	@Test
	public void testBlackKnightMoves() {
		char[][] board = new char[8][8];
		String whitePiecesInput = "Qa1,Pc3,Bc4,Ke4,Pf2,Rh2,Nh4";
		String blackPiecesInput = "Rc8,Qe8,Pa7,Nb6,Kd6,Bf6,Pg6";

		ChessBoard.placePiecesOnBoard(board, whitePiecesInput, true);
		ChessBoard.placePiecesOnBoard(board, blackPiecesInput, false);
		List<String> validMoves = ChessPieceValidator.validateKnightMoves(board, 5, 1, false);
		System.out.println("Black Knight Moves : " + validMoves);
		Set<String> expectedMoves = new HashSet<>();
		expectedMoves.add("c4");
		expectedMoves.add("a4");
		expectedMoves.add("d5");
		expectedMoves.add("d7");
		expectedMoves.add("a8");

		Set<String> actualMoves = new HashSet<>(validMoves);
		assertTrue(actualMoves.equals(expectedMoves));
	}
}