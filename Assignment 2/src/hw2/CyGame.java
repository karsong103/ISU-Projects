package hw2;

/**
 * Model of a Monopoly-like game. Two players take turns
 * rolling dice to move around a board. The game ends
 * when one of the players has at least MONEY_TO_WIN
 * money or one of the players goes bankrupt (they have
 * negative money).
 * 
 * @author Karson Goone
 */
public class CyGame {
	/**
	 * The endzone square type.
	 */
	public static final int ENDZONE = 0;
	/**
	 * The CyTown square type.
	 */
	public static final int CYTOWN = 1;
	/**
	 * The pay rent square type.
	 */
	public static final int PAY_RENT = 2;
	/**
	 * The fall behind square type.
	 */
	public static final int FALL_BEHIND = 3;
	/**
	 * The blizzard square type.
	 */
	public static final int BLIZZARD = 4;
	/**
	 * The pass class square type.
	 */
	public static final int PASS_CLASS = 5;
	/**
	 * Points awarded when landing on or passing over the endzone square.
	 */
	public static final int ENDZONE_PRIZE = 200;
	/**
	 * The standard rent payed to the other player when landing on a
	 * pay rent square.
	 */
	public static final int STANDARD_RENT_PAYMENT = 80;
	/**
	 * The cost to by CyTown.
	 */
	public static final int CYTOWN_COST = 200;
	/**
	 * The amount of money required to win.
	 */
	public static final int MONEY_TO_WIN = 400;


    // Player 1's money
    private int player1Money;
    // Player 2's money
    private int player2Money = 0;

    // Indicates if Player 1 owns CyTown
    private boolean player1Owner = false;
    // Indicates if Player 2 owns CyTown
    private boolean player2Owner = false;

    // Current square of Player 1
    private int player1Square;
    // Current square of Player 2
    private int player2Square;
    // Previous square of the current player
    private int oldSquare = 0;

    // Current player (1 or 2)
    private int currentPlayer = 1;

    // Indicates if Player 1 is affected by a blizzard
    private boolean player1cold = false;
    // Indicates if Player 2 is affected by a blizzard
    private boolean player2cold = false;

    // Total number of squares on the board
    private int numSquares = 0;

	     /**
	     * Constructs a new CyGame with the specified number of squares and starting money for both players.
	     *
	     * @param numSquares    The total number of squares on the board.
	     * @param startingMoney The initial amount of money each player starts with.
	     */
	    public CyGame(int numSquares, int startingMoney) {
	        player1Money = startingMoney;
	        player2Money = startingMoney;
	        this.numSquares = numSquares;
	    }

	    /**
	     * Allows the current player to buy CyTown if they have enough money and are on the correct square.
	     */
	    public void buyCyTown() {
	        if ((currentPlayer - 1) % 2 == 1) {
	        	if (player1Money >= CYTOWN_COST && CYTOWN == getSquareType(player1Square)) {
	        		if (!player2Owner) {
	        			player1Owner = true;
	        			player1Money -= CYTOWN_COST;
	        		}
	        	} else {
	            //System.out.println("Player 1 not enough money for CyTown");
	        	}
	        }
	        else {
	        	if (player2Money >= CYTOWN_COST && CYTOWN == getSquareType(player2Square)) {
	        		if (!player1Owner) {
	        			player2Owner = true;
	        			player2Money -= CYTOWN_COST;
	        		}
	        	} else {
	            //System.out.println("Player 2 not enough money for CyTown");
	        	}
	        }
	    }

	    /**
	     * Ends the current player's turn and switches to the next player.
	     */
	    public void endTurn() {
	        currentPlayer++;
	    }

	    /**
	     * Returns the current player (1 or 2).
	     *
	     * @return The current player.
	     */
	    public int getCurrentPlayer() {
	        if (currentPlayer % 2 == 1) {
	            return 1;
	        } else {
	            return 2;
	        }
	    }

	    /**
	     * Returns the other player (not the current player).
	     *
	     * @return The other player.
	     */
	    public int getOtherPlayer() {
	        if (currentPlayer % 2 == 0) {
	            return 1;
	        } else {
	            return 2;
	        }
	    }

	    /**
	     * Returns the amount of money the specified player has.
	     *
	     * @param player The player (1 or 2) whose money is to be returned.
	     * @return The amount of money the player has.
	     */
	    public int getPlayerMoney(int player) {
	        if (player == 1) {
	            return player1Money;
	        } else {
	            return player2Money;
	        }
	    }

	    /**
	     * Returns the current square of the specified player.
	     *
	     * @param player The player (1 or 2) whose square is to be returned.
	     * @return The current square of the player.
	     */
	    public int getPlayerSquare(int player) {
	        if (player == 1) {
	            return player1Square;
	        } else {
	            return player2Square;
	        }
	    }

	    /**
	     * Determines the type of square based on its position.
	     *
	     * @param square The square number to check.
	     * @return The type of square (e.g., ENDZONE, CYTOWN, PAY_RENT, etc.).
	     */
	    public int getSquareType(int square) {
	        if (square == 0) {
	            return ENDZONE;
	        } else if (square == numSquares - 1) {
	            return CYTOWN;
	        } else if (square % 5 == 0) {
	            return PAY_RENT;
	        } else if (square % 7 == 0 || square % 11 == 0) {
	            return FALL_BEHIND;
	        } else if (square % 3 == 0) {
	            return BLIZZARD;
	        } else {
	            return PASS_CLASS;
	        }
	    }

	    /**
	     * Checks if the game has ended by determining if either player has reached the winning condition.
	     *
	     * @return True if the game has ended, false otherwise.
	     */
	    public boolean isGameEnded() {
	        if ((player1Money >= 400 || player2Money >= 400) || (player1Money < 0 || player2Money < 0)) {
	            return true;
	        }
	        return false;
	    }

	    /**
	     * Checks if Player 1 owns CyTown.
	     *
	     * @return True if Player 1 owns CyTown, false otherwise.
	     */
	    public boolean isPlayer1CyTownOwner() {
	        return player1Owner;
	    }

	    /**
	     * Checks if Player 2 owns CyTown.
	     *
	     * @return True if Player 2 owns CyTown, false otherwise.
	     */
	    public boolean isPlayer2CyTownOwner() {
	        return player2Owner;
	    }

	    /**
	     * Simulates rolling a die and moving the current player's piece based on the value rolled.
	     * Handles special square interactions like ENDZONE, CYTOWN, PAY_RENT, etc.
	     *
	     * @param value The value rolled on the die.
	     */
	    public void roll(int value) {
	        if (isGameEnded() == false) {
	    	if (currentPlayer % 2 == 1) {
	            oldSquare = player1Square;
	            if (player1cold) {
	                if (value % 2 != 0) {
	                    player1Square = (player1Square + value) % numSquares;
	                    player1cold = false;
	                }
	            } else {
	                player1Square = (player1Square + value) % numSquares;
	            }
	            if (ENDZONE == getSquareType(player1Square) || player1Square % numSquares < oldSquare || (player1Square + value) % numSquares == oldSquare) {
	                player1Money += ENDZONE_PRIZE;
	            } else if (CYTOWN == getSquareType(player1Square)) {
	                //System.out.println("Player 1 on CYTOWN");
	            } else if (PAY_RENT == getSquareType(player1Square)) {
	                payrent();
	            } else if (FALL_BEHIND == getSquareType(player1Square)) {
	                fallback();
	            } else if (BLIZZARD == getSquareType(player1Square)) {
	                player1cold = true;
	            } else if (PASS_CLASS == getSquareType(player1Square)) {
	                passclass();
	            }
	        } else {
	            oldSquare = player2Square;
	            if (player2cold) {
	                if (value % 2 != 0) {
	                    player2Square = (player2Square + value) % numSquares;
	                    player2cold = false;
	                }
	            } else {
	                player2Square = (player2Square + value) % numSquares;
	            }
	            if (ENDZONE == getSquareType(player2Square) || player2Square % numSquares < oldSquare || (player2Square + value) % numSquares == oldSquare) {
	                player2Money += ENDZONE_PRIZE;
	            } else if (PAY_RENT == getSquareType(player2Square)) {
	                payrent();
	            } else if (CYTOWN == getSquareType(player2Square)) {
	                //System.out.println("Player 2 on CYTOWN");
	            } else if (FALL_BEHIND == getSquareType(player2Square)) {
	                fallback();
	            } else if (BLIZZARD == getSquareType(player2Square)) {
	                player2cold = true;
	            } else if (PASS_CLASS == getSquareType(player2Square)) {
	                passclass();
	            }
	        }
	        endTurn();
	        }
	    }

	    /**
	     * Simulates the "Pass Class" action, moving the player forward by 4 squares.
	     * Handles interactions with special squares.
	     */
	    private void passclass() {
	        if (currentPlayer % 2 == 1) {
	            oldSquare = player1Square;
	        	player1Square = (player1Square + 4) % numSquares;
	            if (ENDZONE == getSquareType(player1Square) || player1Square % numSquares < oldSquare || (player1Square + 4) % numSquares == oldSquare) {
	                player1Money += ENDZONE_PRIZE;
	            } else if (PAY_RENT == getSquareType(player1Square)) {
	            	payrent();
	            } else if (CYTOWN == getSquareType(player1Square)) {
	                buyCyTown();
	            } else if (FALL_BEHIND == getSquareType(player1Square)) {
	                player1Square -= 1;
	            } else if (BLIZZARD == getSquareType(player1Square)) {
	                player1cold = true;
	            } else if (PASS_CLASS == getSquareType(player1Square)) {
	                // No action needed
	            }
	        } else {
	        	oldSquare = player2Square;
	            player2Square = (player2Square + 4) % numSquares;
	            if (ENDZONE == getSquareType(player2Square) || player2Square % numSquares < oldSquare || (player1Square + 4) % numSquares == oldSquare) {
	                player2Money += ENDZONE_PRIZE;
	            } else if (PAY_RENT == getSquareType(player2Square)) {
	                payrent();
	            } else if (CYTOWN == getSquareType(player2Square)) {
	                buyCyTown();
	            } else if (FALL_BEHIND == getSquareType(player2Square)) {
	                player2Square -= 1;
	            } else if (BLIZZARD == getSquareType(player2Square)) {
	                player2cold = true;
	            } else if (PASS_CLASS == getSquareType(player2Square)) {
	                // No action needed
	            }
	        }
	    }

	    /**
	     * Simulates the "Fall Behind" action, moving the player back by 1 square.
	     * Handles interactions with special squares.
	     */
	    private void fallback() {
	        if (currentPlayer % 2 == 1) {
	            player1Square -= 1;
	            if (ENDZONE == getSquareType(player1Square)) {
	                player2Money += ENDZONE_PRIZE;
	            } else if (PAY_RENT == getSquareType(player1Square)) {
	                payrent();
	            } else if (CYTOWN == getSquareType(player1Square)) {
	                buyCyTown();
	            } else if (FALL_BEHIND == getSquareType(player1Square)) {
	                player1Square -= 1;
	            } else if (BLIZZARD == getSquareType(player1Square)) {
	                player1cold = true;
	            } else if (PASS_CLASS == getSquareType(player1Square)) {
	                player1Square = (player1Square + 4) % numSquares;
	            }
	        } else {
	            player2Square -= 1;
	            if (ENDZONE == getSquareType(player2Square)) {
	                player2Money += ENDZONE_PRIZE;
	            } else if (PAY_RENT == getSquareType(player2Square)) {
	                payrent();
	            } else if (CYTOWN == getSquareType(player2Square)) {
	                buyCyTown();
	            } else if (FALL_BEHIND == getSquareType(player2Square)) {
	                player2Square -= 1;
	            } else if (BLIZZARD == getSquareType(player2Square)) {
	                player2cold = true;
	            } else if (PASS_CLASS == getSquareType(player2Square)) {
	                player2Square = (player2Square + 4) % numSquares;
	            }
	        }
	    }

	    /**
	     * Simulates the "Pay rent" action by checking the turn and taking money away 
	     * and giving it to right player.
	     */
	    private void payrent() {
	    	if (currentPlayer % 2 == 1) {
	    		if (player2Owner) {
	    			player1Money -= 2 * STANDARD_RENT_PAYMENT;
	    			player2Money += 2 * STANDARD_RENT_PAYMENT;
	    		} else {
	    			player1Money -= STANDARD_RENT_PAYMENT;
	    			player2Money += STANDARD_RENT_PAYMENT;
	    		}
	    	}
	    	else {
	    		if (player1Owner) {
	    			player2Money -= 2 * STANDARD_RENT_PAYMENT;
	    			player1Money += 2 * STANDARD_RENT_PAYMENT;
	    		} else {
	    			player2Money -= STANDARD_RENT_PAYMENT;
	    			player1Money += STANDARD_RENT_PAYMENT;
	    		}
	    	}
	    }
	
	// The toString method below is provided for you and you should not modify
	// it. The compile errors will go away after you have written stubs for the
	// rest of the API methods.

	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * <p>
	 * <tt>Player 1*: (0, false, $0) Player 2: (0, false, $0)</tt>
	 * <p>
	 * The asterisks next to the player's name indicates which players turn it
	 * is. The values (0, false, $0) indicate which square the player is on,
	 * if the player is the owner of CyTown, and how much money the player has
	 * respectively.
	 * 
	 * @return one-line string representation of the game state
	 */
	public String toString() {
		String fmt = "Player 1%s: (%d, %b, $%d) Player 2%s: (%d, %b, $%d)";
		String player1Turn = "";
		String player2Turn = "";
		if (getCurrentPlayer() == 1) {
			player1Turn = "*";
		} else {
			player2Turn = "*";
		}
		return String.format(fmt,
				player1Turn, getPlayerSquare(1), isPlayer1CyTownOwner(), getPlayerMoney(1),
				player2Turn, getPlayerSquare(2), isPlayer2CyTownOwner(), getPlayerMoney(2));
	}
}
