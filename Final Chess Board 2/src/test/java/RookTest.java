import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class RookTest {

	@Test
	public void testWhiteRookMoves() {
		char[][] board = new char[8][8];
		String whitePiecesInput = "Qa1,Pc3,Bc4,Ke4,Pf2,Rh2,Nh4";
		String blackPiecesInput = "Rc8,Qe8,Pa7,Nb6,Kd6,Bf6,Pg6";

		ChessBoard.placePiecesOnBoard(board, whitePiecesInput, true);
		ChessBoard.placePiecesOnBoard(board, blackPiecesInput, false);
		List<String> validMoves = ChessPieceValidator.validateRookMoves(board, 1, 7, true);
		System.out.println("White Rook Moves : " + validMoves);
		Set<String> expectedMoves = new HashSet<>();
		expectedMoves.add("h1");
		expectedMoves.add("h3");
		expectedMoves.add("g2");

		Set<String> actualMoves = new HashSet<>(validMoves);
		assertTrue(actualMoves.equals(expectedMoves));
	}

	@Test
	public void testBlackRookMoves() {
		char[][] board = new char[8][8];
		String whitePiecesInput = "Qa1,Pc3,Bc4,Ke4,Pf2,Rh2,Nh4";
		String blackPiecesInput = "Rc8,Qe8,Pa7,Nb6,Kd6,Bf6,Pg6";

		ChessBoard.placePiecesOnBoard(board, whitePiecesInput, true);
		ChessBoard.placePiecesOnBoard(board, blackPiecesInput, false);
		List<String> validMoves = ChessPieceValidator.validateRookMoves(board, 7, 2, false);
		System.out.println("Black Rook Moves : " + validMoves);
		Set<String> expectedMoves = new HashSet<>();
		expectedMoves.add("a8");
		expectedMoves.add("b8");
		expectedMoves.add("d8");
		expectedMoves.add("c7");
		expectedMoves.add("c6");
		expectedMoves.add("c5");
		expectedMoves.add("c4");

		Set<String> actualMoves = new HashSet<>(validMoves);
		assertTrue(actualMoves.equals(expectedMoves));
	}

}