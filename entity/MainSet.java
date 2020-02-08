package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import arrangement.Aggregation;
import enumeration.Number;
import group.Group;


/**
 * メインセットです。
 *
 * @author Ryo Ito
 *
 */
public class MainSet extends Aggregation {

	/**
	 * メインセットです。
	 */
	private List<Cell> mainSet;

	/**
	 * サブセットのリストです。
	 */
	private List<List<Cell>> subSets = new ArrayList<>();

	/**
	 * サブセットのサイズです。
	 */
	private int subSetSize;

	/**
	 * コンストラクタです。
	 *
	 * @param n
	 */
	public MainSet(Group group, int subSetSize) {
		mainSet = group.getCells();
		this.subSetSize = subSetSize;
	}

	/**
	 * サブセットのリストを取得します。
	 *
	 * @return
	 */
	public List<List<Cell>> getSubSets() {
		return subSets;
	}

	/**
	 * サブセットを取得します。
	 *
	 * @param mainSet メインセット
	 * @param indexes 取得するサブセットのインデックス
	 * @return サブセット
	 */
	private List<Cell> getSubSet(List<Cell> mainSet, int... indexes) {
		List<Cell> subset = new ArrayList<>();
		for (int index : indexes) {
			subset.add(mainSet.get(index));
		}
		return subset;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void execute(int... indexes) {
		// セルに入る可能性がある数字を集めたセット
		Set<Number> possibilityNumbers =  new HashSet<>();
		List<Cell> subset = getSubSet(mainSet, indexes);
		for (Cell cell : subset) {
			if (cell.getNumber() != null) {
				return;
			}
			for (Number number : Number.values()) {
				// 入る可能性がある数字の場合
				if (cell.getPossibility(number)) {
					possibilityNumbers.add(number);
				}
			}
		}
		// サブセットのサイズ以下の場合
		if (possibilityNumbers.size() <= subSetSize) {
			subSets.add(subset);
		}
	}
}
