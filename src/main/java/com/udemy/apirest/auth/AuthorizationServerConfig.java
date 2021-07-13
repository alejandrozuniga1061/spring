package com.udemy.apirest.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	@Qualifier("authenticationManager") //Se utiliza para el caso donde exista varios Beans del mismo tipo
	private AuthenticationManager authenticationManager;

	@Autowired
	private InfoAdicionalToken infoAdicional;
	
	/**
	 * Permisos de nuestras rutas de acceso
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()")//permiteAll() para autenticarse, sería publico
			.checkTokenAccess("isAuthenticated()");//Verifica el token y su firma
			
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("angularapp") //cliente
			.secret(passwordEncoder.encode("12345"))
			.scopes("read", "write")//Permiso que tendrìa el cliente
			.authorizedGrantTypes("password", "refresh_token")//El cómo vamos a obtener el token, en nuestro caso cuando inice sesión, hay otros tipo por ejemplo por código autorización
															//El refresh_token permite un token renovado o actualizado para no volver a iniciar sesiòn
			.accessTokenValiditySeconds(3600)//tiempo de caducidad del token va en segundos
			.refreshTokenValiditySeconds(3600);// a la hora vuelve y se regenera el token
		
		//Si se tiene más clientes se debe configurar aquí
			
	}

	
	/**
	 * Valida el token al iniciar sesión
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		//Se registra informacion Adicional 
		//cadena
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicional, accessTokenConverter()));
		
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore())	// es opcional porque en la implementación lo crea
		.accessTokenConverter(accessTokenConverter()) //Componente de manejar el jwt como almacenar los datos del token y traduce los valores codificados para auth 0
		.tokenEnhancer(tokenEnhancerChain);
	}

	@Bean
	public  JwtTokenStore tokenStore() {
		// TODO Auto-generated method stub
		return new JwtTokenStore(accessTokenConverter());//Almacena los datos de autenticación en el token
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVATE);//el que firma el token debe ser la key privada
		jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLIC);// VAlida que el token sea autèntico
		return jwtAccessTokenConverter; 
	}
	
	
	
}
