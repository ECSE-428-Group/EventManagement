import axios from 'axios';

const baseUrl = 'http://localhost:8080';

export const createAccount = async (accountData) => {
    try {
        const account = {
            username: accountData.username,
            password: accountData.password,
            firstname: accountData.firstname,
            lastname: accountData.lastname,
            birthday: accountData.birthday,
            email: accountData.email,
        };

        const accountCreated = await axios.post(
            baseUrl +
                `/userprofile/${accountData.username}` +
                '?password=' +
                accountData.password +
                '&firstName=' +
                accountData.firstname +
                '&lastName=' +
                accountData.lastname +
                '&birthday=' +
                accountData.birthday +
                '&email=' +
                accountData.email
        );
        return accountCreated;
    } catch (error) {
        console.log(error);
        throw new Error(error);
    }
};
