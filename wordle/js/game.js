import Tile from './tile.js';

export default {
    guessesAllowed: 3,
    wordLength: 3,
    currentRowIndex: 0,

    init() {
        console.log('Init called');
        this.board = Array.from({length: this.guessesAllowed}, () => {
            return Array.from({length: this.wordLength}, () => new Tile);
        });
    },

    onKeyPress(key) {
        if (/^[A-z]$/.test(key)) {
            this.fillTile(key);
        }
    },

    fillTile(key) {
        console.log('fillTile called');

        for (let tile of this.currentRow()) {
            if (!tile.letter) {
                tile.letter = key;
                break;
            }
        }

        // if (this.currentTileIndex === this.wordLength - 1) {
        //     this.currentRowIndex++;
        //     this.currentTileIndex = 0;
        // } else {
        //     this.currentTileIndex++;
        // }
    },

    currentRow() {
        return this.board[this.currentRowIndex];
    }
}
