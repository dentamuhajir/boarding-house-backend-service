package id.kostfinder.app.security.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    // minimal 32 bytes atau 32 karakter
    @Value("${jwt.secret}")
    private String SECRET; // idealnya diambil dari environment variable,
    //@Value("${jwt.expired}")
    private long EXPIRATION_TIME = 1000 * 60 * 5;

//    // Mengambil secret key dari application.properties
//    @Value("${jwt.secret}")
//    private String secret;
//
//    // Mengambil waktu kadaluarsa token (dalam milidetik), contoh: 86400000 = 24 jam
//    @Value("${jwt.expiration}")
//    private long expiration;


    // Membuat kunci HMAC dari secret string untuk menandatangani token
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    /**
     * Fungsi untuk membuat JWT token berdasarkan email user
     * @param email email pengguna yang akan menjadi identitas (subject) dalam token
     * @return token JWT dalam bentuk String
     */
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // Subject token = email user
                .setIssuedAt(new Date()) // Tanggal token dibuat
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Waktu kadaluarsa token
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Tanda tangan digital dengan algoritma HS256
                .compact(); // Konversi ke format string
    }

    /**
     * Fungsi untuk mengambil email (subject) dari token JWT
     * @param token JWT dari request Authorization header
     * @return email (subject) yang disimpan dalam token
     */
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Validasi dengan secret key
                .build()
                .parseClaimsJws(token) // Parse token JWT
                .getBody()
                .getSubject(); // Ambil bagian subject = email
    }

    /**
     * Fungsi untuk memeriksa apakah token valid dan belum expired
     * @param token JWT yang dikirim dari client
     * @return true jika token valid dan belum expired, false jika tidak
     */
    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey()) // Gunakan kunci yang sama untuk memvalidasi
                    .build()
                    .parseClaimsJws(token); // Akan error jika token rusak atau tidak valid
            return true;
        } catch (JwtException e) {
            // Jika token rusak, expired, atau tidak cocok dengan signature akan masuk ke sini
            return false;
        }
    }
}
