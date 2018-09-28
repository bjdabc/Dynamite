import java.util.ArrayList;

import com.softwire.dynamite.bot.Bot;
import com.softwire.dynamite.game.Gamestate;
import com.softwire.dynamite.game.Move;
import com.softwire.dynamite.game.Round;
import java.util.Random;

public class BensBot implements Bot {

	int opp_rock = 0;
	int opp_paper = 0;
	int opp_sciss = 0;
	int opp_water = 0;
	int opp_dyn = 0;

	int my_rock = 0;
	int my_paper = 0;
	int my_sciss = 0;
	int my_water = 0;
	int my_dyn = 100;
	ArrayList<Move> list_of_moves;
	int rand_num = 0;
	Move my_move = Move.R;
	
	public Move makeMove(Gamestate gamestate) {

		getRoundInfo(gamestate);
		addMovesToList(gamestate);
		removeMovesFromList(gamestate);
		genRandInt();
		genRandMove();

		return my_move;

	}

	private void genRandMove() {
		my_move = list_of_moves.get(rand_num);

	}

	private void genRandInt() {
		Random rand = new Random();
		rand_num = rand.nextInt(list_of_moves.size());

	}

	private void removeMovesFromList(Gamestate gamestate) {
		if (my_dyn == 0) {
			list_of_moves.remove(Move.D);
		}
		if (opp_dyn == 100) {
			list_of_moves.remove(Move.W);
		}

	}

	private void addMovesToList(Gamestate gamestate) {
		list_of_moves.add(Move.D);
		list_of_moves.add(Move.W);
		list_of_moves.add(Move.R);
		list_of_moves.add(Move.P);
		list_of_moves.add(Move.S);

	}

	public void getRoundInfo(Gamestate gamestate) {
		for (Round round : gamestate.getRounds()) {
			Move p1 = round.getP1();
			Move p2 = round.getP2();

			switch (p1) {
			case R:
				my_rock++;
				break;
			case S:
				my_sciss++;
				break;
			case P:
				my_paper++;
				break;
			case W:
				my_water++;
				break;
			case D:
				my_dyn--;
				break;

			}

			switch (p2) {
			case R:
				opp_rock++;
				break;
			case S:
				opp_sciss++;
				break;
			case P:
				opp_paper++;
				break;
			case W:
				opp_water++;
				break;
			case D:
				opp_dyn--;
				break;

			}

		}
	}
}
