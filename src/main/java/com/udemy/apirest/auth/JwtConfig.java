package com.udemy.apirest.auth;

/**
 * Ir a openssl.org
 * Utilizar los comando de 
 * openssl genrsa -out jwt.pem  Genera la llave privada
 * openssl rsa -in jwt.pem Se visualiza la llave
 * openssl rsa -in jwt.pem -pubout Se genera la llave pùblica
 * @author alejandrozuniga
 *
 */
public class JwtConfig {
	public static final String LLAVE_SECRETA = "clave123";
	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\n" + 
			"MIIEpAIBAAKCAQEAyiztw9wDIIhY/zGsaOscGRcIyGqeHuqZT4wYKCsSOy9QzR4o\n" + 
			"vbYB9LWB7EGwJRbZP8msE04Xrhatx/PhfvkGIy2o5eLtV+Ev3OACSEaat+kjpa7o\n" + 
			"rKgjq/liNPpNNjD0uCP0Hht8l3xFHAx9mGq9YYDi00YgrgQB/3zaNzgl71pVLoOZ\n" + 
			"oWwUj5JQRJ4jEoq1bZJ+wumvMcOgrTrDeTjadR5SlehXuOtoROZsJO5MGUxiPsup\n" + 
			"L0W462H++SrCFFtJky7OZkT6V22mBDSfDR3vMpu4DmxoqCa5dpEYoE0u2rSWpWcD\n" + 
			"1uDaMhskUlg3ZmUw14XKGfszlh5xFVMMMRxlWwIDAQABAoIBAF3uq/Vw1h8aWkcO\n" + 
			"3CzO93oMwq7jIl3434we6eZXxJus6xyx43npA1WfsyKCjoVc7Uj+uS0q/kOLE+iv\n" + 
			"vdgReTGE+FD+swk+fc9gYI8WBrJl5oMHFeGKKTCwqFk25gLEkh6vza7jVGrJeQ6E\n" + 
			"GyJijWSszjYdAff3srnyVyyKsM1YVS73hE2h/TewwfRuOzz/F7smYw1XStPTx5Fh\n" + 
			"3BgWWP7NpudNm1doHF5uiDb074bpNci++ClvDsZL8+tyllzu/HUSl5LvHfiEZ5uD\n" + 
			"lkQYgQDPAgIblVuiyCSubQo3pSceA43svLkzhPNCA8qqCes92GAn+kfZGJiPj2EQ\n" + 
			"A7y7ELECgYEA/vS5SG+rNBEmT8h2PQXNuZumwQN3FA6yg9afyV1d/dEhNVr6pz7d\n" + 
			"0YGFrCEw6sk5KDFWHFD/3ScOfk/mVvMSGmxlizs7TT9rBU0k2VuV9gLvZHyYjCl1\n" + 
			"ADbGAvUPj1JTILqkXIg/scVp7/iRQc+sooxuTZZDv2HkCqIHHgKeZxkCgYEAywDf\n" + 
			"wQeYg4bBATTZq2weWEZ2826ZJym0I6FU9I9wAp/Yx4hsreMcAnm8yjnBtt5qkyn/\n" + 
			"DkPEMjwtai9A2vQtO/9nsBmE59tdIiyqAnt9WpfIfBWcNUjga7YZT3Tn2uq68cS4\n" + 
			"7dRsqQLCC1dzIbNXj8xapnYEg2qDmK4R9DZBApMCgYAqC8HiqEfbtlhTIAQf3C0p\n" + 
			"RvohKqkgravTAjonwzYU/cA0RGTB4CREwM+Cq5adocHDVx+UW+dWWXKScirSfBOo\n" + 
			"N+gnJgWJn8POhAJntirs/17re4DM3Klm96GAK1n9kN4Ur7Mbb3zRnHT0FlIeQFmA\n" + 
			"FRi7wuQ5U7N/7WivEk99QQKBgQCDo6N1RvmtgLqzj5P/R3M+ByOno/aIoNCBsmKm\n" + 
			"3ETkTseCTIM9F2LYcBzufhmR/hTaLYeJtoQJl2BT1SSXV3Plzi9s0H+Q4n04ZgIw\n" + 
			"7edR0c9eN50+KG3vKvyqu8KGAWUM1madQvzbdEBknma4WdXUF85I14sBtRZ08ZwO\n" + 
			"4wexJQKBgQCijzlvqqo6cwIM6ypCYCj0M9lN2UZzaMRsDvFHHWzPuuntZBaweU8m\n" + 
			"I8D8fXSDa1O3vjC/xgKgsohykuAoLxaXWzfIL6UEdzO2QKiX993YzOFGi1fhDutf\n" + 
			"okMLmLMBRIL+qYLDZOs7CysE+IsoACnnR3EFhNPQqT8MePpgsT3LmA==\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyiztw9wDIIhY/zGsaOsc\n" + 
			"GRcIyGqeHuqZT4wYKCsSOy9QzR4ovbYB9LWB7EGwJRbZP8msE04Xrhatx/PhfvkG\n" + 
			"Iy2o5eLtV+Ev3OACSEaat+kjpa7orKgjq/liNPpNNjD0uCP0Hht8l3xFHAx9mGq9\n" + 
			"YYDi00YgrgQB/3zaNzgl71pVLoOZoWwUj5JQRJ4jEoq1bZJ+wumvMcOgrTrDeTja\n" + 
			"dR5SlehXuOtoROZsJO5MGUxiPsupL0W462H++SrCFFtJky7OZkT6V22mBDSfDR3v\n" + 
			"Mpu4DmxoqCa5dpEYoE0u2rSWpWcD1uDaMhskUlg3ZmUw14XKGfszlh5xFVMMMRxl\n" + 
			"WwIDAQAB\n" + 
			"-----END PUBLIC KEY-----";
}
