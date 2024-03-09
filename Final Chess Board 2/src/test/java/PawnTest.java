import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class PawnTest {

	@Test
	public void testWhitePawnMoves() {
		char[][] board = new char[8][8];
		String whitePiecesInput = "Ke4,Qa1,Pf2";
		String blackPiecesInput = "Kd6,Qe8,Pg6";

		ChessBoard.placePiecesOnBoard(board, whitePiecesInput, true);
		ChessBoard.placePiecesOnBoard(board, blackPiecesInput, false);
		List<String> validMoves = ChessPieceValidator.validatePawnMoves(board, 1, 5, true);
		System.out.println("White Pawn Moves : " + validMoves);
		Set<String> expectedMoves = new HashSet<>();
		expectedMoves.add("f3");
		expectedMoves.add("f4");

		Set<String> actualMoves = new HashSet<>(validMoves);
		assertTrue(actualMoves.equals(expectedMoves));
	}

	@Test
	public void testBlackPawnMoves() {
		char[][] board = new char[8][8];
		String whitePiecesInput = "Ke4,Qa1,Pf2";
		String blackPiecesInput = "Kd6,Qe8,Pg6";

		ChessBoard.placePiecesOnBoard(board, whitePiecesInput, true);
		ChessBoard.placePiecesOnBoard(board, blackPiecesInput, false);
		List<String> validMoves = ChessPieceValidator.validatePawnMoves(board, 5, 6, false);
		System.out.println("Black Pawn Moves : " + validMoves);
		Set<String> expectedMoves = new HashSet<>();
		expectedMoves.add("g5");

		Set<String> actualMoves = new HashSet<>(validMoves);
		assertTrue(actualMoves.equals(expectedMoves));
	}

	@Test
	public void testWhitePawnEnPassantMoves() {
		char[][] board = new char[8][8];
		String whitePiecesInput = "Ke4,Qa1,Pf5,Pc4";
		String blackPiecesInput = "Kd6,Qe8,Pb5,Pg6";

		ChessBoard.placePiecesOnBoard(board, whitePiecesInput, true);
		ChessBoard.placePiecesOnBoard(board, blackPiecesInput, false);
		List<String> validMoves = ChessPieceValidator.validatePawnMoves(board, 4, 5, true);
		System.out.println("White Pawn EnPassant Moves : " + validMoves);
		Set<String> expectedMoves = new HashSet<>();
		expectedMoves.add("g6");
		expectedMoves.add("f6");

		Set<String> actualMoves = new HashSet<>(validMoves);
		assertTrue(actualMoves.equals(expectedMoves));
	}

	@Test
	public void testBlackPawnEnPassantMoves() {
		char[][] board = new char[8][8];
		String whitePiecesInput = "Ke4,Qa1,Pf5,Pc4";
		String blackPiecesInput = "Kd6,Qe8,Pb5,Pg6";

		ChessBoard.placePiecesOnBoard(board, whitePiecesInput, true);
		ChessBoard.placePiecesOnBoard(board, blackPiecesInput, false);
		List<String> validMoves = ChessPieceValidator.validatePawnMoves(board, 4, 1, false);
		System.out.println("Black Pawn EnPassant Moves : " + validMoves);
		Set<String> expectedMoves = new HashSet<>();
		expectedMoves.add("c4");
		expectedMoves.add("b4");

		Set<String> actualMoves = new HashSet<>(validMoves);
		assertTrue(actualMoves.equals(expectedMoves));
	}

	@Test
	public void testPawnPromotionMoves() {
		char[][] board = new char[8][8];
		String whitePiecesInput = "Ke1,Pa8";
		String blackPiecesInput = "Ke8";

		ChessBoard.placePiecesOnBoard(board, whitePiecesInput, true);
		ChessBoard.placePiecesOnBoard(board, blackPiecesInput, false);

		List<String> promotionMoves = ChessBoard.getPawnPromotionMoves(7, true);

		assertTrue("Promotion moves should contain Pawn Can Change to Q",
				promotionMoves.contains(" Pawn Can Change to Q"));
		assertTrue("Promotion moves should contain Pawn Can Change to R",
				promotionMoves.contains(" Pawn Can Change to R"));
		assertTrue("Promotion moves should contain Pawn Can Change to B",
				promotionMoves.contains(" Pawn Can Change to B"));
		assertTrue("Promotion moves should contain Pawn Can Change to N",
				promotionMoves.contains(" Pawn Can Change to N"));
	}
}