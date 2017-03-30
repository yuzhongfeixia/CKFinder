package com.afis.jx.web.common;

/**
 * 核心常量类
 * 
 *
 */
public interface CoreConstants {
	/**
	 * 消息处理成功
	 */
	public static final int SUCESS_RESPONSE_CODE = 1;
	public static final String SUCESS_RESPONSE_TEXT = "成功处理";
	/**
	 * 消息处理失败
	 */
	public static final int FAIL_RESPONSE_CODE = 0;
	public static final String FAIL_RESPONSE_TEXT = "处理失败";
	public static final String FAIL_RESPONSE_PARAMTERS = "参数错误";

	/**
	 * 操作员类型
	 * 
	 *
	 */
	public enum OperatortType implements KeyValue {
		Admin("1", "系统管理员"), Trader("2", "交易员");
		public String key;
		public String value;

		public static final String ADMIN = "1";
		public static final String TRADER = "2";

		OperatortType(String key, String value) {
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}
	}
}
