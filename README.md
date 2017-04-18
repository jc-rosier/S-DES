## Synopsis

This project is my Java implementation of the Simplified DES algorithm developed by Professor Edward Schaefer of Santa Clara University. The paper describing the algorithm can be downloaded here : http://mercury.webster.edu/aleshunas/COSC%205130/G-SDES.pdf

A brute-force attack on the S-DES algorithm is very easy. The key is only 10-bit long so there are only 1024 possibilities. 

## Code Example

This implementation assumes that the 8-bit block of plaintext was encoded by an 8-bit encoding charset (like ISO-8859-1).
The S-DES encryption algorithm takes an 8-bit block of plaintext and a 10-bit key as input and produces an 8-bit block of ciphertext as output.

```java
String msg = "your message to encode";
SDES sdes = new SDES("1010011010");
StringBuilder encMsg = new StringBuilder();
for (int i=0; i<msg.length();i++){
	encMsg.append(sdes.encrypt(msg.charAt(i)));
}
```

The S-DES decryption algorithm takes an 8-bit block of ciphertext and the same 10-bit key used to produce that ciphertext as input and produces the original 8-bit block of plaintext.

```java
String encMsg = "encoded message";
SDES sdes = new SDES("1010011010");
StringBuilder msg = new StringBuilder();
for (int i=0; i<encMsg.length();i++){
	msg.append(sdes.decrypt(encMsg.charAt(i)));
}
```

## Motivation

As part of my studies, I had to implement the S-DES algorithm as an introduction to cryptography (classical encryption, hashing and public key encryption).



