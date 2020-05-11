package application.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.security.authentication.AuthenticationServiceException;


public class TokenService {

    private String key;

    public void setKey(String key) {
        this.key = key;
    }


    public TokenAuthentication parseAndCheckToken(final String token) {
        DefaultClaims claims;
        try {
            claims = (DefaultClaims) Jwts.parser().setSigningKey(key).parse(token).getBody();
        } catch (Exception ex) {
            throw new AuthenticationServiceException("Token corrupted");
        }


        Long id = claims.get(TokenData.ID.getValue(), Number.class).longValue();
        String email = claims.get(TokenData.EMAIL.getValue(), String.class);
        String role = claims.get(TokenData.ROLE.getValue(), String.class);

        TokenCustomer customer = new TokenCustomer(id, email, role);

        return new TokenAuthentication(token, true, customer);
    }


}
