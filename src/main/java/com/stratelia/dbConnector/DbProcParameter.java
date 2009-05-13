package com.stratelia.dbConnector;

public class DbProcParameter {

	private boolean isOutParameter = false;  // vrai pour un param�tre de sortie
	private int parameterType; // Type du param�tre si param�tre de sortie
	private Object parameterValue; // valeur du param�tre � utiliser pour un param�tre en entr�e

	public DbProcParameter(boolean _isOutParameter, int _parameterType, Object _parameterValue) {

		isOutParameter = _isOutParameter;
		parameterType = _parameterType;
		parameterValue = _parameterValue;
	}

	public boolean getIsOutParameter() {

		return isOutParameter;
	}
	public void setIsOutParameter(boolean _isOutParameter) {

		isOutParameter = _isOutParameter;
	}

	public int getParameterType() {

		return parameterType;
	}
	public void setParameterType(int _parameterType) {

		parameterType = _parameterType;
	}

	public Object getParameterValue() {

		return parameterValue;
	}
	public void setParameterValue(Object _parameterValue) {

		parameterValue = _parameterValue;
	}

}
