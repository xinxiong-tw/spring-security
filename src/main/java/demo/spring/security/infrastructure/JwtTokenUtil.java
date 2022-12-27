package demo.spring.security.infrastructure;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Base64;

@Component
public class JwtTokenUtil {

    static {
        try {
            KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
            rsa.initialize(2048);
            KeyPair keyPair = rsa.generateKeyPair();
            publicKey = (RSAPublicKey) keyPair.getPublic();
            privateKey = ((RSAPrivateKey) keyPair.getPrivate());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static final RSAPublicKey publicKey;
    private static final RSAPrivateKey privateKey;

    public boolean validate(String token) {
        try {
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .acceptExpiresAt(10) // 10 secs
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getUserName(String token) {
        Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
        JWTVerifier verifier = JWT.require(algorithm)
                .acceptExpiresAt(10)
                .build();

        DecodedJWT decodedJWT = verifier.verify(token);

        return decodedJWT.getClaim("username").asString();
    }

    public String generateToken(UserDetails user) {
        Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
        return JWT.create()
                .withExpiresAt(Instant.now().plusSeconds(10))
                .withClaim("username", user.getUsername())
                .sign(algorithm);
    }

    public static void main(String[] args) {
        try {
            KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
            rsa.initialize(2048);
            KeyPair keyPair = rsa.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            Base64.Encoder encoder = Base64.getEncoder();
            String publicKeyString =
                    "-----BEGIN RSA PUBLIC KEY-----\n"
                            + encoder.encodeToString(publicKey.getEncoded())
                            + "\n-----END RSA PUBLIC KEY-----\n";
            System.out.println(publicKeyString);
            String privateKeyString =
                    "-----BEGIN RSA PRIVATE KEY-----\n"
                            + encoder.encodeToString(privateKey.getEncoded())
                            + "\n-----END RSA PRIVATE KEY-----\n";
            System.out.println(privateKeyString);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
