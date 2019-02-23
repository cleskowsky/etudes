// Chronal Calibration
// Part 1: Find the last frequency after 1 pass through change list
// Part 2: Find a repeated frequency
package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"
)

// Input returns a list of frequency changes
func Input(fname string) []int {
	data := []int{}
	f, err := os.Open(fname)
	if err != nil {
		log.Fatal(err)
	}
	s := bufio.NewScanner(f)
	for s.Scan() {
		n, err := strconv.Atoi(strings.TrimSpace(s.Text()))
		if err != nil {
			log.Fatal(err)
		}
		data = append(data, n)
	}
	return data
}

func main() {
	changes := Input("../../input/1.txt")

	// Part 1
	f := 0
	for _, c := range changes {
		f += c
	}
	fmt.Println(f)

	// Part 2
	seen := make(map[int]int)
	f = 0
	for i, j := 0, 0; ; i, j = (i+1)%len(changes), j+1 {
		f += changes[i]
		if seen[f] > 0 {
			fmt.Println(f, j)
			break
		}
		seen[f]++
	}
}
