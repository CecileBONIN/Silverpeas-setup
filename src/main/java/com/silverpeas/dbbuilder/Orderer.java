package com.silverpeas.dbbuilder;

import java.util.HashMap;

/**
 * Titre :        dbBuilder
 * Description :  Builder des BDs Silverpeas
 * Copyright :    Copyright (c) 2001
 * Soci�t� :      Strat�lia Silverpeas
 * @author ATH
 * @version 1.0
 */

public class Orderer {

        public Orderer() {
        }

	/* ordonne les �l�ments d'un HashMap structur�e comme suit :
	   pour chaque item, la cl� est le nom de l'item, et la valeur un vecteur de string d'items prioritaires
	*/
	public static HashMap order(HashMap hOri) {

		// trace sur la HashMap d'entr�e
		/*
		Iterator result = hOri.keySet().iterator();
		while ( result.hasNext() ) {
		        String key = (String) result.next();
			System.out.print("\n" + key + ">>");
			Object[] o = (Object[]) hOri.get(key);
			if (o != null)
				for (int i=0;i<o.length;i++)
					System.out.print(o[i] + " ; ");
			System.out.println();
		}
		*/

		return null;
	}
}