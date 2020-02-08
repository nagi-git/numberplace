package group;

import java.util.List;

import constants.NumberPlaceConstants;
import entity.Cell;
import enumeration.Number;

/**
 * ブロックです。
 *
 * @author Ryo Ito
 *
 */
public class Block extends Group {

	/**
	 * コンストラクタです。
	 *
	 * @param cells 入るセル
	 */
	public Block(List<Cell> cells) {
		super(cells);
	}

	/**
	 * 番号を算出します。
	 *
	 * @param cell セル
	 */
	public static Number calculateNumber(Cell cell) {
		return Number.of(cell.getLocation() / NumberPlaceConstants.CELL_NUMBER / NumberPlaceConstants.BLOCK_NUMBER * NumberPlaceConstants.BLOCK_NUMBER
				+ cell.getLocation() % NumberPlaceConstants.CELL_NUMBER / NumberPlaceConstants.BLOCK_NUMBER + 1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNumber(Cell cell) {
		// 番号を設定
		this.number = calculateNumber(cell);
	}
}
