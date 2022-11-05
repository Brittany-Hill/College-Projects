var add = require('./index')

describe('addTwoNumbers', () => {
    beforeAll(() => {

    })

    test('add two numbers', () => {
        expect(add.addNumbers(1,2)).toEqual(3);
    })
})