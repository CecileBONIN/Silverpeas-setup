/**
 * Copyright (C) 2000 - 2009 Silverpeas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * As a special exception to the terms and conditions of version 3.0 of
 * the GPL, you may redistribute this Program in connection with Free/Libre
 * Open Source Software ("FLOSS") applications as described in Silverpeas's
 * FLOSS exception.  You should have recieved a copy of the text describing
 * the FLOSS exception, and it is also available here:
 * "http://repository.silverpeas.com/legal/licensing"
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.silverpeas.dbbuilder;

/**
 * Titre : dbBuilder Description : Builder des BDs Silverpeas Copyright :
 * Copyright (c) 2001 Soci�t� : Strat�lia Silverpeas
 * 
 * @author ATH
 * @version 1.0
 */

public class DBBuilderDBProcPiece extends DBBuilderPiece {

  private String dbProcName = null;

  // contructeurs non utilis�s
  private DBBuilderDBProcPiece(String pieceName, String actionName,
      boolean traceMode) throws Exception {
    super(pieceName, actionName, traceMode);
  }

  private DBBuilderDBProcPiece(String pieceName, String actionName,
      String content, boolean traceMode) throws Exception {
    super(pieceName, actionName, content, traceMode);
  }

  private DBBuilderDBProcPiece(String actionInternalID, String pieceName,
      String actionName, int itemOrder, boolean traceMode) throws Exception {
    super(actionInternalID, pieceName, actionName, itemOrder, traceMode);
  }

  // Contructeur utilis� pour une pi�ce de type fichier
  public DBBuilderDBProcPiece(String fileName, String actionName,
      boolean traceMode, String dbProcName) throws Exception {

    super(fileName, actionName, traceMode);
    moreInitialize(dbProcName);
  }

  // Contructeur utilis� pour une pi�ce de type cha�ne en m�moire
  public DBBuilderDBProcPiece(String pieceName, String actionName,
      String content, boolean traceMode, String dbProcName) throws Exception {

    super(pieceName, actionName, content, traceMode);
    moreInitialize(dbProcName);
  }

  // Contructeur utilis� pour une pi�ce stock�e en base de donn�es
  public DBBuilderDBProcPiece(String actionInternalID, String pieceName,
      String actionName, int itemOrder, boolean traceMode, String dbProcName)
      throws Exception {

    super(actionInternalID, pieceName, actionName, itemOrder, traceMode);
    moreInitialize(dbProcName);
  }

  private void moreInitialize(String dbProcName) throws Exception {

    if (dbProcName == null)
      throw new Exception("Missing <dbprocname> tag for \"fileName\" item.");

    this.dbProcName = dbProcName;

    setInstructions();
  }

  public void setInstructions() {

    instructions = new Instruction[3];
    instructions[0] = new Instruction(Instruction.IN_UPDATE, getContent(), null);
    instructions[1] = new Instruction(Instruction.IN_CALLDBPROC,
        this.dbProcName, null);
    instructions[2] = new Instruction(Instruction.IN_UPDATE, "DROP PROCEDURE "
        + this.dbProcName, null);
  }

  public void cacheIntoDB(String _package, int _itemOrder) throws Exception {

    cacheIntoDB(_package, _itemOrder, DBBuilderFileItem.FILEATTRIBDBPROC_VALUE,
        null, null, dbProcName);
  }

}