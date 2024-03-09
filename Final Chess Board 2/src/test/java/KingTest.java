import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class KingTest {

	@Test
	public void testWhiteKingMoves() {
		char[][] board = new char[8][8];
		String whitePiecesInput = "Qa1,Pc3,Bc4,Ke4,Pf2,Rh2,Nh4";
		String blackPiecesInput = "Rc8,Qe8,Pa7,Nb6,Kd6,Bf6,Pg6";

		ChessBoard.placePiecesOnBoard(board, whitePiecesInput, true);
		ChessBoard.placePiecesOnBoard(board, blackPiecesInput, false);
		List<String> validMoves = ChessPieceValidator.validateKingMoves(board, 3, 4, true);
		System.out.println("White King Moves : " + validMoves);
		Set<String> expectedMoves = new HashSet<>();
		expectedMoves.add("f3");
		expectedMoves.add("f4");
		expectedMoves.add("d3");

		Set<String> actualMoves = new HashSet<>(validMoves);
		assertEquals(expectedMoves, actualMoves);
	}

	@Test
	public void testBlackKingMoves() {
		char[][] board = new char[8][8];
		String whitePiecesInput = "Qa1,Pc3,Bc4,Ke4,Pf2,Rh2,Nh4";
		String blackPiecesInput = "Rc8,Qe8,Pa7,Nb6,Kd6,Bf6,Pg6";

		ChessBoard.placePiecesOnBoard(board, whitePiecesInput, true);
		ChessBoard.placePiecesOnBoard(board, blackPiecesInput, false);
		List<String> validMoves = ChessPieceValidator.validateKingMoves(board, 5, 3, false);
		System.out.println("Black King Moves : " + validMoves);
		Set<String> expectedMoves = new HashSet<>();

		expectedMoves.add("d7");
		expectedMoves.add("c7");
		expectedMoves.add("e7");
		expectedMoves.add("c6");
		expectedMoves.add("c5");

		Set<String> actualMoves = new HashSet<>(validMoves);
		assertEquals(expectedMoves, actualMoves);
	}

	@Test
	public void testWhiteKingCheckMoves() {
		char[][] board = new char[8][8];
		String whitePiecesInput = "Ke7,Qf2";
		String blackPiecesInput = "Kb7,Qa3,Pc7";

		ChessBoard.placePiecesOnBoard(board, whitePiecesInput, true);
		ChessBoard.placePiecesOnBoard(board, blackPiecesInput, false);
		List<String> validMoves = ChessPieceValidator.validateKingMoves(board, 6, 4, true);
		System.out.println("White King Check Moves : " + validMoves);
		Set<String> expectedMoves = new HashSet<>();
		expectedMoves.add("d7");
		expectedMoves.add("e8");
		expectedMoves.add("f7");
		expectedMoves.add("f6");
		expectedMoves.add("e6");
		expectedMoves.add("d8");

		Set<String> actualMoves = new HashSet<>(validMoves);
		assertEquals(expectedMoves, actualMoves);
	}

	@Test
	public void testBlackKingCheckMoves() {
		char[][] board = new char[8][8];
		String whitePiecesInput = "Kd8,Qg2,Pc7";
		String blackPiecesInput = "Kb7,Qc4";

		ChessBoard.placePiecesOnBoard(board, whitePiecesInput, true);
		ChessBoard.placePiecesOnBoard(board, blackPiecesInput, false);
		List<String> validMoves = ChessPieceValidator.validateKingMoves(board, 6, 1, false);
		System.out.println("Black King Check Moves : " + validMoves);
		Set<String> expectedMoves = new HashSet<>();

		expectedMoves.add("a7");
		expectedMoves.add("a6");
		expectedMoves.add("b6");

		Set<String> actualMoves = new HashSet<>(validMoves);
		assertEquals(expectedMoves, actualMoves);
	}

	@Test
	public void testBlackKingCheckMateMoves() {
		char[][] board = new char[8][8];
		String whitePiecesInput = "Ke1,Qe7,Re3,Ra4,Ba3,Ng6";
		String blackPiecesInput = "Ke8,Rc4,Bc2,Bb8,Pf4";

		ChessBoard.placePiecesOnBoard(board, whitePiecesInput, true);
		ChessBoard.placePiecesOnBoard(board, blackPiecesInput, false);

		String validMoves = ChessBoard.calculateValidMoves(board, Arrays.asList(whitePiecesInput.split(",")),
				Arrays.asList(blackPiecesInput.split(",")), "Ke8");
		System.out.println("White King CheckMate : " + validMoves);
		assertEquals("Checkmate!", validMoves);
	}

	@Test
	public void testWhiteKingCheckMateMoves() {
		char[][] board = new char[8][8];
		String whitePiecesInput = "Ke1,Rb6,Ng1,Bc2,Pc6";
		String blackPiecesInput = "Ke8,Qg3,Rd7,Nf1,Bb4,Ba6";

		ChessBoard.placePiecesOnBoard(board, whitePiecesInput, true);
		ChessBoard.placePiecesOnBoard(board, blackPiecesInput, false);

		String validMoves = ChessBoard.calculateValidMoves(board, Arrays.asList(whitePiecesInput.split(",")),
				Arrays.asList(blackPiecesInput.split(",")), "Ke1");
		System.out.println("Black King CheckMate : " + validMoves);
		assertEquals("Checkmate!", validMoves);
	}

}