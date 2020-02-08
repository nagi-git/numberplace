package execution;

import java.util.Map;

import constants.NumberPlaceConstants;
import entity.Cell;
import group.Table;
import logic.InputLogic;
import logic.NumberPlaceLogic;
import logic.OutputLogic;

public class Execution {

	public static void main(String[] args) {
		InputLogic inputLogic = new InputLogic();
		OutputLogic outputLogic = new OutputLogic();

		// テストデータを入力
		inputLogic.inputNumber();
		// テストデータのマップを取得
		Map<Integer, Cell> cellMap = inputLogic.numberToEntityList();
		// テーブルにマップをセット
		Table table = new Table(cellMap);
		NumberPlaceLogic numberPlaceLogic = new NumberPlaceLogic(table);

		boolean isChanged = false;
		do {
			do {
				do {
					do {
						do {
							do {
								do {
									numberPlaceLogic.logic0();
									outputLogic.displayNumber(cellMap);

									if (table.isFinished()) {
										outputLogic.displayNumber(cellMap);
										System.out.println(NumberPlaceConstants.MESSAGE_COMPLETE);
										return;
									}

									outputLogic.displayPossibility(cellMap);
									isChanged = numberPlaceLogic.logic1();
								} while (isChanged);

								isChanged = numberPlaceLogic.logic2();
							} while (isChanged);

							isChanged = numberPlaceLogic.logic3();
						} while (isChanged);

						isChanged = numberPlaceLogic.logic4();
					} while (isChanged);

					isChanged = numberPlaceLogic.logic5();
				} while (isChanged);

				isChanged = numberPlaceLogic.logic6();
			} while (isChanged);

			isChanged = numberPlaceLogic.logic7();
		} while (isChanged);

		System.out.println(NumberPlaceConstants.MESSAGE_DEFECTIVE);
	}

}
