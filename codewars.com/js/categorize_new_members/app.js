function openOrSenior(data) {
    let result = [];
    data.forEach((member) => {
        let age = member[0];
        let handicap = member[1];
        result.push((age >= 55 && handicap > 7) ? 'Senior' : 'Open');
    });
    return result;
}
module.exports = openOrSenior;
