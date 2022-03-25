import React, {useState} from 'react';

// MUI
import { Grid, TextField, Typography, Button } from '@material-ui/core';

export default function EditProfileUpdateInfo( {handleEditProfile} ) {
    const baseURL = "https://event-management-app-backend.herokuapp.com/";
    const baseURLTesting = "http://localhost:8080/";
    
    const inputs = {
        newPassword: 'Password',
        firstname: 'First Name',
        lastname: 'Last Name',
        email: 'Email'
    };

    const [username, setUsername] = useState("");
    const [curPassword, setCurPassword] = useState("");
    const [newPassword, setNewPassword] = useState("");
    const [firstName, setNewFirstName] = useState("");
    const [lastName, setNewLastName] = useState("");
    const [email, setNewEmail] = useState("");
    const [birthday, setNewBirthday] = useState("");

    const editProfileData = {username,curPassword,newPassword,firstName,lastName,birthday,email};

    const textFields = Object.values(inputs).map((data, idx) => {
        return (
            <Grid
                style={{
                    padding: '5px 0px',
                }}
            >
                <Typography>Update {data}</Typography>
                <TextField
                    label={`${data}`}
                    variant='outlined'
                    fullWidth
                    size='small'
                    inputProps={{
                        style: {
                            fontSize: 12,
                        },
                    }} // font size of input text
                    InputLabelProps={{
                        style: {
                            fontSize: 12,
                        },
                    }} // font size of input label
                    onChange={e => handleChange(e, idx)}
                />
            </Grid>
        );
    });
    return (
        <>
            <Grid
                container
                direction='row'
                justify='flex-start'
                alignItems='center'
                style={{ margin: 30 }}
                item
                xs={10}
            >
                <Grid item xs={10}>
                    {textFields}
                </Grid>
            </Grid>
            <Grid
                style={{ padding: '60px 10px 0px 0px' }}
                container
                justifyContent='flex-end'
                alignItems='flex-end'
            >
                <Button
                    onClick = {updateUser} 
                    variant='outlined'
                    style={{ backgroundColor: '#6558f5', color: '#ffffff' }}
                >
                    Update Profile
                </Button>
            </Grid>
        </>
    );

    

    function handleChange(e, idx) {
        if ((idx) === 0) {
            setNewPassword(e.target.value);
        } else if ((idx) === 1) {
            setNewFirstName(e.target.value);
        } else if ((idx) === 2) {
            setNewLastName(e.target.value);
        } else if ((idx) === 3) {
            setNewEmail(e.target.value);
        }
        console.log(editProfileData)
    }

    function updateUser() {
        setUsername(localStorage.getItem('username'));
        setCurPassword(localStorage.getItem('password'));


        // if (editProfileData.newPassword === "") {
        //     getPassword();
        // }
        // if (editProfileData.firstName === "") {
        //     getFirstName();
        // }
        // if (editProfileData.lastName === "") {
        //     getLastName();
        // }
        // if (editProfileData.email === "") {
        //     getEmail();
        // }
        // if (editProfileData.birthday === "") {
        //     getBirthday();
        // }

        handleEditProfile(editProfileData);
        getPassword();
        if(editProfileData.newPassword != localStorage.getItem('password')) {
            localStorage.setItem('password',editProfileData.newPassword);
            console.log(editProfileData.newPassword);
        }

    }

    function getPassword() {
        fetch(baseURLTesting + "users/" + localStorage.getItem('username')).then((result) => {
            result.json().then((resp) => {
                setNewPassword(resp.password);
            })
        }).catch(error => console.log("ERROR OCCURRED"))
    }

    function getFirstName() {
        fetch(baseURLTesting + "users/" + localStorage.getItem('username')).then((result) => {
            result.json().then((resp) => {
                setNewFirstName(resp.firstName);
            })
        }).catch(error => console.log("ERROR OCCURRED"))
    }

    function getLastName() {
        fetch(baseURLTesting + "users/" + localStorage.getItem('username')).then((result) => {
            result.json().then((resp) => {
                setNewLastName(resp.lastName);
            })
        }).catch(error => console.log("ERROR OCCURRED"))
    }

    function getBirthday() {
        fetch(baseURLTesting + "users/" + localStorage.getItem('username')).then((result) => {
            result.json().then((resp) => {
                setNewBirthday((resp.birthday).substring(0,10));
            })
        }).catch(error => console.log("ERROR OCCURRED"))
    }

    function getEmail() {
        fetch(baseURLTesting + "users/" + localStorage.getItem('username')).then((result) => {
            result.json().then((resp) => {
                setNewEmail(resp.email);
            })
        }).catch(error => console.log("ERROR OCCURRED"))
    }
}
