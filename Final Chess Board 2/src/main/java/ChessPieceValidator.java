import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChessPieceValidator {
	public static List<String> calculateLegalMoves(char[][] board, char pieceType, int row, int col, boolean isWhite) {
		switch (Character.toUpperCase(pieceType)) {

		case 'K':
			return validateKingMoves(board, row, col, isWhite);

		case 'Q':
			return validateQueenMoves(board, row, col, isWhite);

		case 'R':
			return validateRookMoves(board, row, col, isWhite);

		case 'B':
			return validateBishopMoves(board, row, col, isWhite);

		case 'N':
			return validateKnightMoves(board, row, col, isWhite);

		case 'P':
			return validatePawnMoves(board, row, col, isWhite);
		default:
			System.out.println("Invalid piece type.");
			return new ArrayList<>();
		}
	}

	public static List<String> validateQueenMoves(char[][] board, int row, int col, boolean isWhite) {
		List<String> validMoves = new ArrayList<>();

		// Check horizontal and vertical moves
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) {
					continue; // Skip current position
				}
				validateDirection(board, row, col, i, j, isWhite, validMoves);
			}
		}

		// Check diagonal moves
		for (int i = -1; i <= 1; i += 2) {
			for (int j = -1; j <= 1; j += 2) {
				validateDirection(board, row, col, i, j, isWhite, validMoves);
			}
		}

		// Remove duplicates
		Set<String> uniqueMoves = new HashSet<>(validMoves);
		validMoves.clear();
		validMoves.addAll(uniqueMoves);

		return validMoves;
	}

	public static List<String> validateRookMoves(char[][] board, int row, int col, boolean isWhite) {
		List<String> validMoves = new ArrayList<>();

		// Check vertical moves upwards
		for (int i = row + 1; i < board.length; i++) {
			if (!processMove(board, i, col, isWhite, validMoves)) {
				break;
			}
		}

		// Check vertical moves downwards
		for (int i = row - 1; i >= 0; i--) {
			if (!processMove(board, i, col, isWhite, validMoves)) {
				break;
			}
		}

		// Check horizontal moves to the right
		for (int j = col + 1; j < board[row].length; j++) {
			if (!processMove(board, row, j, isWhite, validMoves)) {
				break;
			}
		}

		// Check horizontal moves to the left
		for (int j = col - 1; j >= 0; j--) {
			if (!processMove(board, row, j, isWhite, validMoves)) {
				break;
			}
		}

		return validMoves;
	}

	public static List<String> validateBishopMoves(char[][] board, int row, int col, boolean isWhite) {
		List<String> validMoves = new ArrayList<>();

		// Check diagonal moves (up-right)
		for (int i = row + 1, j = col + 1; i < board.length && j < board[row].length; i++, j++) {
			if (!processMove(board, i, j, isWhite, validMoves)) {
				break;
			}
		}

		// Check diagonal moves (up-left)
		for (int i = row + 1, j = col - 1; i < board.length && j >= 0; i++, j--) {
			if (!processMove(board, i, j, isWhite, validMoves)) {
                break;
            }
		}

		// Check diagonal moves (down-right)
		for (int i = row - 1, j = col + 1; i >= 0 && j < board[row].length; i--, j++) {
			if (!processMove(board, i, j, isWhite, validMoves)) {
				break;
			}
		}

		// Check diagonal moves (down-left)
		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
			if (!processMove(board, i, j, isWhite, validMoves)) {
				break;
			}
		}

		return validMoves;
	}

	public static List<String> validateKnightMoves(char[][] board, int row, int col, boolean isWhite) {
		List<String> validMoves = new ArrayList<>();

		int[][] moves = { { -2, -1 }, { -2, 1 }, { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 }, { 2, -1 }, { 2, 1 } };

		for (int[] move : moves) {
			int newRow = row + move[0];
			int newCol = col + move[1];
			if (isValidPosition(newRow, newCol)
					&& (board[newRow][newCol] == '\u0000' || isOpponent(board[newRow][newCol], isWhite))) {
				validMoves.add(String.valueOf((char) ('a' + newCol)) + (newRow + 1));
			}
		}

		return validMoves;
	}

	public static List<String> validatePawnMoves(char[][] board, int row, int col, boolean isWhite) {
		List<String> validMoves = new ArrayList<>();

		int direction = isWhite ? 1 : -1;
		int initialRow = isWhite ? 1 : 6;

		// Moving one square forward
		if (isValidPosition(row + direction, col) && board[row + direction][col] == '\u0000') {
			validMoves.add(String.valueOf((char) ('a' + col)) + (row + direction + 1));

			// Moving two squares forward if pawn hasn't moved yet
			if (row == initialRow && board[row + 2 * direction][col] == '\u0000') {
				validMoves.add(String.valueOf((char) ('a' + col)) + (row + 2 * direction + 1));
			}
		}

		// Capturing diagonally
		for (int captureCol : new int[] { col - 1, col + 1 }) {
			if (isValidPosition(row + direction, captureCol)
					&& isOpponent(board[row + direction][captureCol], isWhite)) {
				validMoves.add(String.valueOf((char) ('a' + captureCol)) + (row + direction + 1));
			}

			// En passant capture
			if (isValidPosition(row, captureCol) && board[row][captureCol] != '\u0000' && board[row][captureCol] != 'P'
					&& board[row][captureCol] != 'p') {
				char adjacentPiece = board[row][captureCol];
				char pawnToCapture = isWhite ? 'p' : 'P';
				if ((isWhite && row == 4 && adjacentPiece == pawnToCapture)
						|| (!isWhite && row == 3 && adjacentPiece == pawnToCapture)) {
                    validMoves.add(String.valueOf((char) ('a' + captureCol)) + (row + direction + 1));
                }
			}
		}

		return validMoves;
	}

	public static List<String> validateKingMoves(char[][] board, int row, int col, boolean isWhite) {
		List<String> validMoves = new ArrayList<>();

		int[][] directions = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

		for (int[] dir : directions) {
			int newRow = row + dir[0];
			int newCol = col + dir[1];

			if (isValidPosition(newRow, newCol)
					&& (board[newRow][newCol] == '\u0000' || isOpponent(board[newRow][newCol], isWhite))) {

				// Ensure the king is not moving adjacent to opponent's king
				if (!isSquareAdjacentToOpponentKing(board, newRow, newCol, isWhite)) {
					// Check if the square is under attack by opponent's pieces
					if (!isSquareUnderAttack(board, newRow, newCol, !isWhite)
							&& !isPawnAttackingSquare(board, newRow, newCol, isWhite)) {

						// Simulate the move
						char originalPiece = board[newRow][newCol];
						board[newRow][newCol] = board[row][col];
						board[row][col] = '\u0000';

						// Check if the king is still in check after the move
						if (!isKingInCheck(board, isWhite)) {
							validMoves.add(String.valueOf((char) ('a' + newCol)) + (newRow + 1));
						}

						// Undo the move
						board[row][col] = board[newRow][newCol];
						board[newRow][newCol] = originalPiece;
					}
				}
			}
		}

		return validMoves;
	}

	public static boolean isPawnAttackingSquare(char[][] board, int row, int col, boolean isAttackerWhite) {
		// Determine the direction of pawn movement based on its color
		int direction = isAttackerWhite ? 1 : -1;

		// Check if the square is diagonally adjacent to a pawn and is not empty
		for (int dCol = -1; dCol <= 1; dCol += 2) {
			int attackRow = row + direction;
			int attackCol = col + dCol;
			if (isValidPosition(attackRow, attackCol) && board[attackRow][attackCol] != '\u0000') {
				char piece = board[attackRow][attackCol];
				if ((isAttackerWhite && Character.isLowerCase(piece) && piece == 'p')
						|| (!isAttackerWhite && Character.isUpperCase(piece) && piece == 'P')) {
					return true;
				}
			}
		}

		return false;
	}

	protected static boolean isKingInCheck(char[][] board, boolean isWhite) {
		// Find the position of the king
		int kingRow = -1;
		int kingCol = -1;
		char king = isWhite ? 'K' : 'k';

		outerLoop: for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == king) {
					kingRow = i;
					kingCol = j;
					break outerLoop;
				}
			}
		}
		boolean isCheck = false;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (isOpponent(board[i][j], isWhite)) {
					List<String> moves = calculateLegalMoves(board, board[i][j], i, j, !isWhite);
					if (moves.contains(String.valueOf((char) ('a' + kingCol)) + (kingRow + 1))) {
						isCheck = true;
						break;
					}
				}
			}
		}

		return isCheck;
	}

	public static boolean isSquareAdjacentToOpponentKing(char[][] board, int row, int col, boolean isAttackerWhite) {
		// Find the opponent's king
		char opponentKing = isAttackerWhite ? 'k' : 'K';

		// Iterate through the surrounding squares
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				int newRow = row + i;
				int newCol = col + j;

				if (isValidPosition(newRow, newCol) && board[newRow][newCol] == opponentKing) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isSquareUnderAttack(char[][] board, int row, int col, boolean isAttackerWhite) {
		// Opponent color is opposite of the attacker's color
		boolean isOpponentWhite = !isAttackerWhite;

		// Check if any opponent's piece can attack the square
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (isOpponent(board[i][j], isOpponentWhite)) {
					List<String> moves = calculateLegalMoves(board, board[i][j], i, j, isOpponentWhite);
					for (String move : moves) {
						int attackRow = move.charAt(1) - '1';
						int attackCol = move.charAt(0) - 'a';
						if (attackRow == row && attackCol == col) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	private static boolean isValidPosition(int row, int col) {
		return row >= 0 && row < 8 && col >= 0 && col < 8;
	}

	private static boolean isOpponent(char piece, boolean isWhite) {
		if (isWhite) {
			return Character.isLowerCase(piece) && piece != 'k';
		} else {
			return Character.isUpperCase(piece) && piece != 'K';
		}
	}

	private static void validateDirection(char[][] board, int row, int col, int rowIncrement, int colIncrement,
			boolean isWhite, List<String> validMoves) {
		int newRow = row + rowIncrement;
		int newCol = col + colIncrement;

		while (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
			if (!processMove(board, newRow, newCol, isWhite, validMoves)) {
				break;
			}
			newRow += rowIncrement;
			newCol += colIncrement;
		}
	}

	private static boolean processMove(char[][] board, int row, int col, boolean isWhite, List<String> validMoves) {
		char pieceAtPosition = board[row][col];
		if (pieceAtPosition == '\u0000') {
			// Empty square, continue exploring
			validMoves.add(String.valueOf((char) ('a' + col)) + (row + 1));
			return true;
		} else if (isWhite != Character.isUpperCase(pieceAtPosition)) {
			// Opponent piece encountered, add its position and stop
			validMoves.add(String.valueOf((char) ('a' + col)) + (row + 1));
			return false;
		} else {
			return false;
		}
	}
}