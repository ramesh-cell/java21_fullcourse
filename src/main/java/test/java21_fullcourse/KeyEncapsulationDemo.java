//// File: KeyEncapsulationDemo.java
//
//import javax.crypto.KEM;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//import java.security.KeyPair;
//import java.security.KeyPairGenerator;
//import java.security.PublicKey;
//import java.util.HexFormat;
//
//public class KeyEncapsulationDemo {
//    public static void main(String[] args) throws Exception {
//        // 1. Generate an X25519 key pair (used for key encapsulation)
//        KeyPairGenerator kpg = KeyPairGenerator.getInstance("X25519");
//        KeyPair kp = kpg.generateKeyPair();
//
//        // 2. Get a Key Encapsulation Mechanism instance
//        KEM kem = KEM.getInstance("DHKEM", "X25519");
//
//        // 3. Define the secret derivation algorithm (e.g., HKDF with SHA-256)
//        KEM.Encapsulator encapsulator = kem.newEncapsulator(kp.getPublic(), "HKDF", "HmacSHA256");
//
//        // 4. Encapsulate a secret
//        KEM.Encapsulated encapsulated = encapsulator.encapsulate(32); // 32-byte secret key
//        byte[] sharedSecret = encapsulated.key().getEncoded();
//
//        // 5. Show the encapsulated key and shared secret
//        System.out.println("Encapsulated Key: " + HexFormat.of().formatHex(encapsulated.encapsulation()));
//        System.out.println("Shared Secret    : " + HexFormat.of().formatHex(sharedSecret));
//
//        // 6. Decapsulate using the private key
//        KEM.Decapsulator decapsulator = kem.newDecapsulator(kp.getPrivate(), "HKDF", "HmacSHA256");
//        SecretKey recovered = decapsulator.decapsulate(encapsulated.encapsulation(), 32);
//
//        System.out.println("Recovered Secret : " + HexFormat.of().formatHex(recovered.getEncoded()));
//    }
//}
