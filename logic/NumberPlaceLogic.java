package logic;

import java.util.List;

import entity.Cell;
import group.Block;
import group.Column;
import group.Row;
import group.Table;


/**
 * ロジック
 *
 * @author Ryo Ito
 *
 */
public class NumberPlaceLogic {

	/**
	 * 問題を解く対象のテーブルです。
	 */
	private Table table;

	/**
	 * グループのロジック 行、列、ブロック
	 */
	private GroupLogic groupLogic = new GroupLogic();

	/**
	 * コンストラクタです。
	 *
	 * @param table 問題を解く対象のテーブル
	 */
	public NumberPlaceLogic(Table table) {
		this.table = table;
	}

	/**
	 * ロジック０です。
	 *
	 * @param cellMap 全セルマップ
	 * @return 更新有無
	 */
	public void logic0() {
		// 行毎のリストを取得
		List<Row> rowList = table.rowGroup();
		// 列毎のリストを取得
		List<Column> columnList = table.columnGroup();
		// ブロック毎のリストを取得
		List<Block> blockList = table.blockGroup();

		for (Row row : rowList) {
			groupLogic.groupExistence(row);
		}
		for (Column column : columnList) {
			groupLogic.groupExistence(column);
		}
		for (Block block : blockList) {
			groupLogic.groupExistence(block);
		}
	}

	/**
	 * ロジック１です。
	 *
	 * @return 更新有無
	 */
	public boolean logic1() {
		// 行毎のリストを取得
		List<Row> rowList = table.rowGroup();
		// 列毎のリストを取得
		List<Column> columnList = table.columnGroup();
		// ブロック毎のリストを取得
		List<Block> blockList = table.blockGroup();

		boolean isChanged = false;
		for (Row row : rowList) {
			isChanged = groupLogic.onlyOneNumberPossibility(row);
		}
		for (Column column : columnList) {
			isChanged = groupLogic.onlyOneNumberPossibility(column) || isChanged;
		}
		for (Block block : blockList) {
			isChanged = groupLogic.onlyOneNumberPossibility(block) || isChanged;
		}
		return isChanged;
	}

	/**
	 * ロジック２です。
	 *
	 * @return 更新有無
	 */
	public boolean logic2() {
		// 行毎のリストを取得
		List<Row> rowList = table.rowGroup();
		// 列毎のリストを取得
		List<Column> columnList = table.columnGroup();
		// ブロック毎のリストを取得
		List<Block> blockList = table.blockGroup();

		boolean isChanged = false;
		for (Row row : rowList) {
			isChanged = groupLogic.onlyOneCellPossibility(row);
		}
		for (Column column : columnList) {
			isChanged = groupLogic.onlyOneCellPossibility(column) || isChanged;
		}
		for (Block block : blockList) {
			isChanged = groupLogic.onlyOneCellPossibility(block) || isChanged;
		}
		return isChanged;
	}

	/**
	 * ロジック３です。
	 *
	 * @param cellMap 全セルマップ
	 * @return 更新有無
	 */
	public boolean logic3() {
		// 行毎のリストを取得
		List<Row> rowList = table.rowGroup();
		// 列毎のリストを取得
		List<Column> columnList = table.columnGroup();
		// ブロック毎のリストを取得
		List<Block> blockList = table.blockGroup();

		boolean isChanged = false;
		for (Block block : blockList) {
			for (Row row : rowList) {
				for (Cell cell : row.getCells()) {
					if (block.getCells().contains(cell)) {
						isChanged = isChanged | groupLogic.sameLinePossibility(block, row);
						break;
					}
				}
			}
			for (Column column : columnList) {
				for (Cell cell : column.getCells()) {
					if (block.getCells().contains(cell)) {
						isChanged = isChanged | groupLogic.sameLinePossibility(block, column);
						break;
					}
				}
			}
		}
		return isChanged;
	}

	/**
	 * ロジック４です。
	 *
	 * @param cellMap 全セルマップ
	 * @return 更新有無
	 */
	public boolean logic4() {
		// 行毎のリストを取得
		List<Row> rowList = table.rowGroup();
		// 列毎のリストを取得
		List<Column> columnList = table.columnGroup();
		// ブロック毎のリストを取得
		List<Block> blockList = table.blockGroup();

		boolean isChanged = false;
		for (Block block : blockList) {
			for (Row row : rowList) {
				for (Cell cell : row.getCells()) {
					if (block.getCells().contains(cell)) {
						isChanged = isChanged | groupLogic.oneLinePossibilityInBlock(row, block);
						break;
					}
				}
			}
			for (Column column : columnList) {
				for (Cell cell : column.getCells()) {
					if (block.getCells().contains(cell)) {
						isChanged = isChanged | groupLogic.oneLinePossibilityInBlock(column, block);
						break;
					}
				}
			}
		}
		return isChanged;
	}

	/**
	 * ロジック５です。
	 *
	 * @param cellMap 全セルマップ
	 * @return 更新有無
	 */
	public boolean logic5() {
		// 行毎のリストを取得
		List<Row> rowList = table.rowGroup();
		// 列毎のリストを取得
		List<Column> columnList = table.columnGroup();
		// ブロック毎のリストを取得
		List<Block> blockList = table.blockGroup();

		boolean isChanged = false;
		for (Row row : rowList) {
			isChanged = groupLogic.subSet(row, 2);
		}
		for (Column column : columnList) {
			isChanged = groupLogic.subSet(column, 2) || isChanged;
		}
		for (Block block : blockList) {
			isChanged = groupLogic.subSet(block, 2) || isChanged;
		}
		return isChanged;
	}

	/**
	 * ロジック６です。
	 *
	 * @param cellMap 全セルマップ
	 * @return 更新有無
	 */
	public boolean logic6() {
		// 行毎のリストを取得
		List<Row> rowList = table.rowGroup();
		// 列毎のリストを取得
		List<Column> columnList = table.columnGroup();
		// ブロック毎のリストを取得
		List<Block> blockList = table.blockGroup();

		boolean isChanged = false;
		for (Row row : rowList) {
			isChanged = groupLogic.subSet(row, 3);
		}
		for (Column column : columnList) {
			isChanged = groupLogic.subSet(column, 3) || isChanged;
		}
		for (Block block : blockList) {
			isChanged = groupLogic.subSet(block, 3) || isChanged;
		}
		return isChanged;
	}

	/**
	 * ロジック７です。
	 *
	 * @param cellMap 全セルマップ
	 * @return 更新有無
	 */
	public boolean logic7() {
		// 行毎のリストを取得
		List<Row> rowList = table.rowGroup();
		// 列毎のリストを取得
		List<Column> columnList = table.columnGroup();
		// ブロック毎のリストを取得
		List<Block> blockList = table.blockGroup();

		boolean isChanged = false;
		for (Row row : rowList) {
			isChanged = groupLogic.subSet(row, 4);
		}
		for (Column column : columnList) {
			isChanged = groupLogic.subSet(column, 4) || isChanged;
		}
		for (Block block : blockList) {
			isChanged = groupLogic.subSet(block, 4) || isChanged;
		}
		return isChanged;
	}
}
