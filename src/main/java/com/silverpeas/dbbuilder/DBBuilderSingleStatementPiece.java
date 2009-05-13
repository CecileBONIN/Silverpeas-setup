package com.silverpeas.dbbuilder;

/**
 * Titre :        dbBuilder
 * Description :  Builder des BDs Silverpeas
 * Copyright :    Copyright (c) 2001
 * Soci�t� :      Strat�lia Silverpeas
 * @author ATH
 * @version 1.0
 */

public class DBBuilderSingleStatementPiece extends DBBuilderPiece {


	// Contructeur utilis� pour une pi�ce de type fichier
        public DBBuilderSingleStatementPiece(String pieceName, String actionName, boolean traceMode) throws Exception {

		super(pieceName, actionName, traceMode);
		setInstructions();
        }

	// Contructeur utilis� pour une pi�ce de type cha�ne en m�moire
        public DBBuilderSingleStatementPiece(String pieceName, String actionName, String content, boolean traceMode) throws Exception {

		super(pieceName, actionName, content, traceMode);
		setInstructions();
        }

	// Contructeur utilis� pour une pi�ce stock�e en base de donn�es
        public DBBuilderSingleStatementPiece(String actionInternalID, String pieceName, String actionName, int itemOrder, boolean traceMode) throws Exception {
		super(actionInternalID, pieceName, actionName, itemOrder, traceMode);
		setInstructions();
        }

	public void setInstructions() {

		instructions = new Instruction[1];
		instructions[0].setInstructionType(Instruction.IN_UPDATE);
		instructions[0].setInstructionText(getContent());
	}

	public void cacheIntoDB(String _package, int _itemOrder) throws Exception {

		cacheIntoDB(_package, _itemOrder, DBBuilderFileItem.FILEATTRIBSTATEMENT_VALUE, null, null, null);
	}

}
