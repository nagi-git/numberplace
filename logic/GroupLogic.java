package logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import arrangement.Combination;
import constants.NumberPlaceConstants;
import entity.Cell;
import entity.MainSet;
import enumeration.Number;
import group.Block;
import group.Group;

/**
 * グループのロジック
 * 行、列、ブロック
 *
 * @author Ryo Ito
 *
 */
public class GroupLogic {

	/**
	 * セルに入る数字が確定している場合、同じグループ内にあるセルに同じ数字が入る可能性をなくします。<br>
	 *
	 * ┏━┯━┯━┳━┯━┯━┳━┯━┯━┓<br>
	 * ┃？│Ｘ│Ｘ┃Ｘ│Ｘ│Ｘ┃Ｘ│Ｘ│Ｘ┃<br>
	 * ┠─┼─┼─╂─┼─┼─╂─┼─┼─┨<br>
	 * ┃Ｘ│Ｘ│Ｘ┃　│　│　┃　│　│　┃<br>
	 * ┠─┼─┼─╂─┼─┼─╂─┼─┼─┨<br>
	 * ┃Ｘ│Ｘ│Ｘ┃　│　│　┃　│　│　┃<br>
	 * ┣━┿━┿━╋━┿━┿━╋━┿━┿━┫<br>
	 *
	 * ？に入る数字が確定している場合、Ｘに？と同じ数字は入らない。
	 *
	 * @param group １グループ（行、列またはブロック）
	 */
	public void groupExistence(Group group) {
		for (Cell cell1 : group.getCells()) {
			// 入る数字が確定している場合
			if (cell1.getNumber() != null) {
				for (Cell cell2 : group.getCells()) {
					// 他のセルに同じ数字が入る可能性はなし
					cell2.isImpossibile(cell1.getNumber());
				}
			}
		}
	}

	/**
	 * 同じグループ内で数字が入る可能性が１セルのみの場合、その数字で確定します。<br>
	 *
	 * ┏━┯━┯━┳━┯━┯━┳━┯━┯━┓<br>
	 * ┃？│２│３┃　│　│　┃　│　│　┃<br>
	 * ┠─┼─┼─╂─┼─┼─╂─┼─┼─┨<br>
	 * ┃　│　│　┃１│　│　┃　│　│　┃<br>
	 * ┠─┼─┼─╂─┼─┼─╂─┼─┼─┨<br>
	 * ┃　│　│　┃　│　│　┃１│　│　┃<br>
	 * ┣━┿━┿━╋━┿━┿━╋━┿━┿━┫<br>
	 *
	 * ？に入る数字は１で確定する。
	 *
	 * @param group １グループ（行、列またはブロック）
	 */
	public boolean onlyOneNumberPossibility(Group group) {
		boolean isChanged = false;
		for (Number number : Number.values()) {
			List<Cell> possibilityCells = new ArrayList<>();
			for (Cell cell : group.getCells()) {
				// 数字が入る可能性があるセルの場合
				if (cell.getPossibility(number)) {
					// リストに追加
					possibilityCells.add(cell);
				}
			}

			// １セルのみの場合
			if (possibilityCells.size() == 1) {
				// 入る数字を確定
				possibilityCells.get(0).setNumber(number);
				isChanged = true;
			}
		}
		return isChanged;
	}

	/**
	 * 同じグループ内で入る可能性がある数字が１つだけの場合、その数字で確定します。<br>
	 *
	 * ┏━┯━┯━┳━┯━┯━┳━┯━┯━┓<br>
	 * ┃？│２│３┃４│５│６┃　│　│　┃<br>
	 * ┠─┼─┼─╂─┼─┼─╂─┼─┼─┨<br>
	 * ┃７│８│９┃　│　│　┃　│　│　┃<br>
	 * ┠─┼─┼─╂─┼─┼─╂─┼─┼─┨<br>
	 * ┃　│　│　┃　│　│　┃　│　│　┃<br>
	 * ┣━┿━┿━╋━┿━┿━╋━┿━┿━┫<br>
	 *
	 * ？に入る数字は１で確定する。
	 *
	 * @param group １グループ（行、列またはブロック）
	 */
	public boolean onlyOneCellPossibility(Group group) {
		boolean isChanged = false;
		for (Cell cell : group.getCells()) {
			List<Number> possibilityNumbers = new ArrayList<>();
			for (Number number : Number.values()) {
				// 入る可能性がある数字の場合
				if (cell.getPossibility(number)) {
					// リストに追加
					possibilityNumbers.add(number);
				}
			}

			// 数字が１つだけの場合
			if (possibilityNumbers.size() == 1) {
				// 入る数字を確定
				cell.setNumber(possibilityNumbers.get(0));
				isChanged = true;
			}
		}
		return isChanged;
	}

	/**
	 * 同じブロック内で数字が入る可能性があるセルが１ライン（行または列）に並ぶ場合、
	 * 他のブロックで同じライン（行または列）に数字が入る可能性をなくします。<br>
	 *
	 * ┏━┯━┯━┳━┯━┯━┳━┯━┯━┓<br>
	 * ┃　│　│　┃Ｘ│Ｘ│Ｘ┃Ｘ│Ｘ│Ｘ┃<br>
	 * ┠─┼─┼─╂─┼─┼─╂─┼─┼─┨<br>
	 * ┃４│５│６┃　│　│　┃　│　│　┃<br>
	 * ┠─┼─┼─╂─┼─┼─╂─┼─┼─┨<br>
	 * ┃７│８│９┃　│　│　┃　│　│　┃<br>
	 * ┣━┿━┿━╋━┿━┿━╋━┿━┿━┫<br>
	 *
	 * Ｘには１、２、３のいずれも入らない。
	 *
	 * @param block ブロック
	 * @param line ブロックと交わる１ライン（行または列）
	 */
	public boolean sameLinePossibility(Block block, Group line) {
		boolean isChanged = false;
		for (int i = 0; i < NumberPlaceConstants.BLOCK_NUMBER; i++) {
			for (Number number : Number.values()) {
				List<Cell> blockCells = block.getCells();
				List<Cell> possibilityCells =  new ArrayList<>();
				for (Cell cell : blockCells) {
					// 数字が入る可能性があるセルの場合
					if (cell.getPossibility(number)) {
						possibilityCells.add(cell);
					}
				}

				// 数字が入る可能性があるセルがない場合
				if (possibilityCells.isEmpty()) {
					continue;
				}

				List<Cell> lineCells = line.getCells();
				// 数字が入る可能性が全て同じラインの場合
				if (lineCells.containsAll(possibilityCells)) {
					for (Cell cell : lineCells) {
						// ブロックが異なる場合
						if (!blockCells.contains(cell)) {
							isChanged = isChanged || cell.getPossibility(number);
							cell.isImpossibile(number);
						}
					}
				}
			}
		}
		return isChanged;
	}

	/**
	 * １ライン（行または列）で数字が入る可能性が１ブロック内のみの場合、
	 * 同じブロック内で他のライン（行または列）に同じ数字が入る可能性をなくします。<br>
	 *
	 * ┏━┯━┯━┳━┯━┯━┳━┯━┯━┓<br>
	 * ┃　│　│　┃　│５│６┃　│８│９┃<br>
	 * ┠─┼─┼─╂─┼─┼─╂─┼─┼─┨<br>
	 * ┃Ｘ│Ｘ│Ｘ┃　│　│　┃　│　│　┃<br>
	 * ┠─┼─┼─╂─┼─┼─╂─┼─┼─┨<br>
	 * ┃Ｘ│Ｘ│Ｘ┃　│　│　┃　│　│　┃<br>
	 * ┣━┿━┿━╋━┿━┿━╋━┿━┿━┫<br>
	 * ┃　│　│　┃１│　│　┃　│　│　┃<br>
	 * ┠─┼─┼─╂─┼─┼─╂─┼─┼─┨<br>
	 * ┃　│　│　┃　│　│　┃１│　│　┃<br>
	 * ┠─┼─┼─╂─┼─┼─╂─┼─┼─┨<br>
	 *
	 * Ｘには１が入らない。
	 *
	 * @param line ライン（行または列）
	 * @param block ラインと交わる１ブロック
	 */
	public boolean oneLinePossibilityInBlock(Group line, Block block) {
		boolean isChanged = false;
		for (int i = 0; i < NumberPlaceConstants.BLOCK_NUMBER; i++) {
			for (Number number : Number.values()) {
				List<Cell> lineCells = line.getCells();
				List<Cell> possibilityCells =  new ArrayList<>();
				for (Cell cell : lineCells) {
					// 数字が入る可能性があるセルの場合
					if (cell.getPossibility(number)) {
						possibilityCells.add(cell);
					}
				}
				// 数字が入る可能性があるセルがない場合
				if (possibilityCells.isEmpty()) {
					continue;
				}

				List<Cell> blockCells = block.getCells();
				// 数字が入る可能性が全て同じブロックの場合
				if (blockCells.containsAll(possibilityCells)) {
					for (Cell cell : blockCells) {
						// ラインが異なる場合
						if (!lineCells.contains(cell)) {
							isChanged = isChanged || cell.getPossibility(number);
							cell.isImpossibile(number);
						}
					}
				}
			}
		}
		return isChanged;
	}

	/**
	 * サブセットのサイズが２の場合の例。<br>
	 * 同じグループ内で２つの数字が入る可能性が２セルのみの場合、
	 * その他のセルにその２つの数字が入る可能性をなくします。<br>
	 *
	 * ┏━┯━┯━┳━┯━┯━┳━┯━┯━┓<br>
	 * ┃？│？│Ｘ┃　│　│　┃　│　│　┃<br>
	 * ┠─┼─┼─╂─┼─┼─╂─┼─┼─┨<br>
	 * ┃Ｘ│Ｘ│Ｘ┃　│　│　┃　│　│　┃<br>
	 * ┠─┼─┼─╂─┼─┼─╂─┼─┼─┨<br>
	 * ┃Ｘ│Ｘ│Ｘ┃　│　│　┃　│　│　┃<br>
	 * ┣━┿━┿━╋━┿━┿━╋━┿━┿━┫<br>
	 * ┃　│　│　┃　│　│　┃　│　│　┃<br>
	 * ┠─┼─┼─╂─┼─┼─╂─┼─┼─┨<br>
	 *
	 * ？に入る可能性がある数字が１or２だけの場合、Ｘには１も２も入らない。
	 *
	 * @param cellList １行または１列、１ブロックのセル
	 * @param size サブセットのサイズ
	 */
	public boolean subSet(Group group, int size) {
		boolean isChanged = false;
		MainSet mainSet = new MainSet(group, size);
		Combination.combine(group.getCells(), size, mainSet);
		List<List<Cell>> subSets = mainSet.getSubSets();

		if (subSets.isEmpty()) {
			return isChanged;
		}

		for (List<Cell> subSet : subSets) {
			// セルに入る可能性がある数字を集めたセット
			Set<Number> possibilityNumbers =  new HashSet<>();
			for (Cell subCell : subSet) {
				for (Number number : Number.values()) {
					// 入る可能性がある数字の場合
					if (subCell.getPossibility(number)) {
						possibilityNumbers.add(number);
					}
				}
			}

			for (Cell cell : group.getCells()) {
				if (subSet.contains(cell)) {
					continue;
				}
				for (Number number : possibilityNumbers) {
					isChanged = isChanged || cell.getPossibility(number);

					if (cell.getPossibility(number))
						System.out.print("");
					cell.isImpossibile(number);
				}
			}
		}
		return isChanged;
	}
}
