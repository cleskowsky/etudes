package main

import (
	"bytes"
	"crypto/md5"
	"fmt"
	"io"
	"strconv"
	"strings"
)

// FindPassword returns the 8 character
// code that will open the inner door ...
func FindPassword(dID string) string {
	passwd := []byte{'.', '.', '.', '.', '.', '.', '.', '.'}

	n := 0
	for {
		h := md5.New()
		io.WriteString(h, dID+strconv.Itoa(n))
		digest := fmt.Sprintf("%x", h.Sum(nil))
		if strings.HasPrefix(digest, "00000") {
			pos, err := strconv.Atoi(string(digest[5]))
			if err == nil && pos < 8 {
				if passwd[pos] == '.' {
					passwd[pos] = digest[6]
					fmt.Println(digest, string(passwd))
				}

				if !bytes.Contains(passwd, []byte{'.'}) {
					return string(passwd)
				}
			}
		}
		n++
	}
}

func main() {
	fmt.Println(FindPassword("wtnhxymk"))
}
