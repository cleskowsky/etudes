import Tile from './tile.js';

export default {
    guessesAllowed: 3,
    theWord: 'cat',
    currentRowIndex: 0,

    init() {
        this.board = Array.from({length: this.guessesAllowed}, () => {
            return Array.from({length: this.theWord.length}, () => new Tile);
        });
    },

    onKeyPress(key) {
        if (/^[A-z]$/.test(key)) {
            this.fillTile(key);
        } else if (key === 'Enter') {
            this.submitGuess();
        }
    },

    fillTile(key) {
        for (let tile of this.currentRow) {
            if (!tile.letter) {
                tile.letter = key;
                break;
            }
        }
    },

    get currentRow() {
        return this.board[this.currentRowIndex];
    },

    submitGuess() {
        let guess = this.currentGuess;
        if (guess.length < this.theWord.length) {
            return;
        }

        if (guess === 'cat') {
            console.log('win');
        } else {
            console.log('nope');
            this.currentRowIndex++;
        }
    },

    get currentGuess() {
        return this.currentRow.map(tile => tile.letter).join('');
    }
}
