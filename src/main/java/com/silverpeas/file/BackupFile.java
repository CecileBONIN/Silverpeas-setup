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
package com.silverpeas.file;

import java.io.File;
import java.util.ArrayList;

public class BackupFile {
  private static final String EXT = ".bak~";
  private File file = null;
  private File[] listFileBackup = null;
  private File firstFileBackup = null;
  private File lastFileBackup = null;
  private int nbFileBackup = 0;
  private int lastNumFileBackup = 0;

  public BackupFile(File pathname) throws Exception {
    file = pathname;
    if (!file.exists()) {
      throw new Exception("file not found : " + file.getAbsolutePath());
    }
    refresh();
  }

  public void makeBackup() throws Exception {
  }

  private void refresh() throws Exception {
    setList();
    setFirst();
    setLast();
  }

  public File[] getListBackup() throws Exception {
    return listFileBackup;
  }

  public File getFirstBackup() throws Exception {
    return firstFileBackup;
  }

  // ---------------------------------------------------------------------

  /**
   * @return
   * @throws Exception
   * @see
   */
  public File getLastBackup() throws Exception {
    return lastFileBackup;
  }

  // ---------------------------------------------------------------------

  /**
   * @return
   * @throws Exception
   * @see
   */
  public int getNumberBackup() throws Exception {
    return nbFileBackup;
  }

  // ---------------------------------------------------------------------

  /**
   * @return
   * @throws Exception
   * @see
   */
  public boolean existBackup() throws Exception {
    return (nbFileBackup != 0);
  }

  // ---------------------------------------------------------------------

  /**
   * @throws Exception
   * @see
   */
  private void setList() throws Exception {
    File[] listeAll = file.getParentFile().listFiles();
    ArrayList listeGood = new ArrayList();
    for (int i = 0; i < listeAll.length; i++) {
      File tmpFile = listeAll[i];
      if (tmpFile.isFile() && (tmpFile.getName().indexOf(file.getName()) != -1)
          && tmpFile.getName().indexOf(EXT) != -1) {
        listeGood.add(tmpFile);
      }
    }
    File[] listeF = new File[listeGood.size()];
    for (int i = 0; i < listeF.length; i++) {
      listeF[i] = (File) listeGood.get(i);
    }
    listFileBackup = listeF;
    nbFileBackup = listFileBackup.length;
  }

  // ---------------------------------------------------------------------

  /**
   * @throws Exception
   * @see
   */
  private void setFirst() throws Exception {
    File f = null;
    if (this.existBackup()) {
      f = listFileBackup[0];
      for (int i = 0; i < nbFileBackup; i++) {
        if (listFileBackup[i].lastModified() < f.lastModified()) {
          f = listFileBackup[i];
        }
      }
    }
    firstFileBackup = f;
  }

  // ---------------------------------------------------------------------

  /**
   * @throws Exception
   * @see
   */
  private void setLast() throws Exception {
    lastFileBackup = null;
    if (this.existBackup()) {
      lastFileBackup = listFileBackup[0];
      for (int i = 0; i < nbFileBackup; i++) {
        if (listFileBackup[i].lastModified() > lastFileBackup.lastModified()) {
          lastFileBackup = listFileBackup[i];
        }
      }
    }
    lastNumFileBackup = -1;
    if (this.existBackup()) {
      String name = lastFileBackup.getName();
      String endName = name.substring(name.lastIndexOf(EXT) + EXT.length(),
          name.length());
      int i = 1;
      while (i <= endName.length()) {
        try {
          int num = Integer.parseInt(endName.substring(0, i));
          lastNumFileBackup = num;
          i++;
        } catch (Exception e) {
          i = endName.length() + 2;
        }
      }
    }
  }

  // ---------------------------------------------------------------------

  /**
   * @param args
   * @throws Exception
   * @see
   */
  public static void main(String[] args) throws Exception {
    BackupFile bf = new BackupFile(new File(args[0]));
    bf.makeBackup();
    File f[] = bf.getListBackup();
    for (int i = 0; i < f.length; i++) {
      System.out.println(f[i].getAbsolutePath());
    }
    System.out.println("last : " + bf.getLastBackup().getAbsolutePath());
    System.out.println("nb last : " + bf.lastNumFileBackup);
  }

}
