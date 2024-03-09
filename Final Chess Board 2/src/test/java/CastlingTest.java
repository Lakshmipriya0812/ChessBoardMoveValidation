import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class CastlingTest {

	@Test
	public void testBothSideWhiteCastling() {
		char[][] gameBoard = new char[8][8];
		String whitePiecesInput = "Ke1,Ra1,Rh1";
		ChessBoard.placePiecesOnBoard(gameBoard, whitePiecesInput, true);

		String blackPiecesInput = "Ke8,Ra8";
		ChessBoard.placePiecesOnBoard(gameBoard, blackPiecesInput, false);

		String pieceToMove = "Ke1";
		String validMoves = ChessBoard.calculateValidMoves(gameBoard, Arrays.asList(whitePiecesInput.split(",")),
				Arrays.asList(blackPiecesInput.split(",")), pieceToMove);

		System.out.println("Castling White Both Sides: " + validMoves);
		assertTrue(validMoves.contains("g1"));
		assertTrue(validMoves.contains("f1"));
		assertTrue(validMoves.contains("f2"));
		assertTrue(validMoves.contains("d1"));
		assertTrue(validMoves.contains("e2"));
		assertTrue(validMoves.contains("d2"));
		assertTrue(validMoves.contains("c1"));
	}

	@Test
	public void testBothSideBlackCastling() {
		char[][] gameBoard = new char[8][8];
		String whitePiecesInput = "Ke1,Ra1,Rh1";
		ChessBoard.placePiecesOnBoard(gameBoard, whitePiecesInput, true);

		String blackPiecesInput = "Ke8,Ra8,Rh8";
		ChessBoard.placePiecesOnBoard(gameBoard, blackPiecesInput, false);

		String pieceToMove = "Ke8";
		String validMoves = ChessBoard.calculateValidMoves(gameBoard, Arrays.asList(whitePiecesInput.split(",")),
				Arrays.asList(blackPiecesInput.split(",")), pieceToMove);

		System.out.println("Castling Black Both Sides: " + validMoves);
		assertTrue(validMoves.contains("g8"));
		assertTrue(validMoves.contains("f7"));
		assertTrue(validMoves.contains("f8"));
		assertTrue(validMoves.contains("d7"));
		assertTrue(validMoves.contains("e7"));
		assertTrue(validMoves.contains("d8"));
		assertTrue(validMoves.contains("c8"));
	}

	@Test
	public void testRightSideWhiteCastling() {
		char[][] gameBoard = new char[8][8];
		String whitePiecesInput = "Ke1,Bc1,Rh1";
		ChessBoard.placePiecesOnBoard(gameBoard, whitePiecesInput, true);

		String blackPiecesInput = "Ke8,Ra8";
		ChessBoard.placePiecesOnBoard(gameBoard, blackPiecesInput, false);

		String pieceToMove = "Ke1";
		String validMoves = ChessBoard.calculateValidMoves(gameBoard, Arrays.asList(whitePiecesInput.split(",")),
				Arrays.asList(blackPiecesInput.split(",")), pieceToMove);

		System.out.println("Castling White Right Side: " + validMoves);
		assertTrue(validMoves.contains("g1"));
		assertTrue(validMoves.contains("f1"));
		assertTrue(validMoves.contains("f2"));
		assertTrue(validMoves.contains("d1"));
		assertTrue(validMoves.contains("e2"));
		assertTrue(validMoves.contains("d2"));
	}

	@Test
	public void testRightSideBlackCastling() {
		char[][] gameBoard = new char[8][8];
		String whitePiecesInput = "Ke1,Ra1,Rh1";
		ChessBoard.placePiecesOnBoard(gameBoard, whitePiecesInput, true);

		String blackPiecesInput = "Ke8,Ra8,Bf1";
		ChessBoard.placePiecesOnBoard(gameBoard, blackPiecesInput, false);

		String pieceToMove = "Ke8";

		String validMoves = ChessBoard.calculateValidMoves(gameBoard, Arrays.asList(whitePiecesInput.split(",")),
				Arrays.asList(blackPiecesInput.split(",")), pieceToMove);

		System.out.println("Castling Black Right Side: " + validMoves);
		assertTrue(validMoves.contains("f7"));
		assertTrue(validMoves.contains("f8"));
		assertTrue(validMoves.contains("d7"));
		assertTrue(validMoves.contains("e7"));
		assertTrue(validMoves.contains("d8"));
		assertTrue(validMoves.contains("c8"));
	}

	@Test
	public void testLeftSideWhiteCastling() {
		char[][] gameBoard = new char[8][8];
		String whitePiecesInput = "Ke1,Bf1,Ra1";
		ChessBoard.placePiecesOnBoard(gameBoard, whitePiecesInput, true);

		String blackPiecesInput = "Ke8,Ra8";
		ChessBoard.placePiecesOnBoard(gameBoard, blackPiecesInput, false);

		String pieceToMove = "Ke1";

		String validMoves = ChessBoard.calculateValidMoves(gameBoard, Arrays.asList(whitePiecesInput.split(",")),
				Arrays.asList(blackPiecesInput.split(",")), pieceToMove);

		System.out.println("Castling White Left Side: " + validMoves);

		assertTrue(validMoves.contains("c1"));
		assertTrue(validMoves.contains("f2"));
		assertTrue(validMoves.contains("d1"));
		assertTrue(validMoves.contains("e2"));
		assertTrue(validMoves.contains("d2"));
	}

	@Test
	public void testLeftSideBlackCastling() {
		char[][] gameBoard = new char[8][8];
		String whitePiecesInput = "Ke1,Ra1,Rh1";
		ChessBoard.placePiecesOnBoard(gameBoard, whitePiecesInput, true);

		String blackPiecesInput = "Ke8,Rh8,Bb1";
		ChessBoard.placePiecesOnBoard(gameBoard, blackPiecesInput, false);

		String pieceToMove = "Ke8";

		String validMoves = ChessBoard.calculateValidMoves(gameBoard, Arrays.asList(whitePiecesInput.split(",")),
				Arrays.asList(blackPiecesInput.split(",")), pieceToMove);

		System.out.println("Castling Black Left Side: " + validMoves);

		assertTrue(validMoves.contains("f7"));
		assertTrue(validMoves.contains("f8"));
		assertTrue(validMoves.contains("d7"));
		assertTrue(validMoves.contains("e7"));
		assertTrue(validMoves.contains("d8"));
		assertTrue(validMoves.contains("g8"));
	}

}