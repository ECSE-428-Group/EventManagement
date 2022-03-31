import React, {useState, useEffect} from 'react';

// MUI
import { Grid, TextField, Typography, Button } from '@material-ui/core';

// Moment
import moment from 'moment';

export default function EditProfileUpdateInfo( {handleEditProfile} ) {
    const baseURL = "https://event-management-app-backend.herokuapp.com/";
    const baseURLTesting = "http://localhost:8080/";

    const input = {
        data: [
            'Current Password',
            'New Password',
            'Confirm New Password',
            'Update First Name',
            'Update Last Name',
            'Update Birthday',
            'Update Email',
        ],
        label: [
            'Enter Current Password',
            'At least 6 characters',
            'Repeat your password',
            'First name',
            'Last name',
            'YYYY-MM-DD',
            'user@email.com',
        ],
        page: '/editProfile',
        type: 'Edit Profile',
        button: 'Update Profile',
    };

    const INITIAL_FORM_ERRORS = {
        currentpassword: null,
        password: null,
        confirmpassword: null,
        firstname: null,
        lastname: null,
        birthday: null,
        email: null,
    };

    const [formErrors, setFormErrors] = useState(INITIAL_FORM_ERRORS);

    const username = localStorage.getItem('username');
    const [curPassword, setCurPassword] = useState("");
    const [newPassword, setNewPassword] = useState("");
    const [firstName, setNewFirstName] = useState("");
    const [lastName, setNewLastName] = useState("");
    const [email, setNewEmail] = useState("");
    const [birthday, setNewBirthday] = useState("");
    const [disableButton, setDisableButton] = useState(false);

    const [confirmPassword, setConfirmPassword] = useState("");

    const editProfileData = {username,curPassword,newPassword,firstName,lastName,birthday,email};

    const textFields = input.data.map((data, idx) => {
        return (
            <Grid
                style={{
                    padding: '5px 0px',
                }}
            >
                <Typography>{data}</Typography>
                <TextField
                    label={`${input.label[idx]}`}
                    error={
                        formErrors !== undefined &&
                        Object.values(formErrors)[idx] != undefined
                            ? true
                            : false
                    }
                    helperText={
                        formErrors !== undefined &&
                        Object.values(formErrors)[idx]
                    }
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
    
    useEffect(() => {
        checkEmail();
        checkDate();
        checkPasswords();
        checkButton();
    },[newPassword,confirmPassword,birthday,email]);

    function checkButton() {
        if(
            Object.values(textFields).every(value => {
            if (value == "") {
                return true;
            }
            return false;
        }) 
        || (
            Object.values(formErrors).every(value => { // check if all form errors are null
            if (value == null) {
                return true
            }
            return false
        }))) {
            setDisableButton(false);
        } else {
            setDisableButton(true);
        }
    }
    function handleChange(e, idx) {
        if ((idx) === 0) {
            setCurPassword(e.target.value);
        } else if ((idx) === 1) {
            setNewPassword(e.target.value);
        } else if ((idx) === 2) {
            setConfirmPassword(e.target.value);
        } else if ((idx) === 3) {
            setNewFirstName(e.target.value);
        } else if ((idx) === 4) {
            setNewLastName(e.target.value);
        } else if ((idx) === 5) {
            setNewBirthday(e.target.value);
        } else if ((idx) === 6) {
            setNewEmail(e.target.value);
        }
    }

    function updateUser() {

        if(formErrors == null) {

            const status = handleEditProfile(editProfileData);
            if (status === 200) {
                refreshPage();
            }
        }
    }

    function checkDate() {
        if (editProfileData == undefined) {
        } else {

            const validDate = moment(
                editProfileData.birthday,
                'YYYY-MM-DD',
                true
            ).isValid();

            if (
                validDate ||
                editProfileData.birthday == '' ||
                editProfileData.birthday == null
            ) {
                setFormErrors((p) => ({
                    ...p,
                    birthday: null,
                }));
            } else {
                setFormErrors((p) => ({ ...p, birthday: 'Invalid Birthday' }));
            }
        }
    };

    function checkPasswords() {
        if (editProfileData == undefined) {
        } else {
                if ((editProfileData.newPassword !== confirmPassword) && (confirmPassword.length > 0)) {
                setFormErrors((p) => ({
                    ...p,
                    confirmpassword: 'Passwords do not match',
                }));
            } else {
                setFormErrors((p) => ({ ...p, confirmpassword: null }));
            }

            if (
                editProfileData.newPassword == undefined ||
                (editProfileData.newPassword.length >= 6 && editProfileData.newPassword.length <= 26) ||
                editProfileData.newPassword == ''
            ) {
                setFormErrors((p) => ({
                    ...p,
                    password: null,
                }));
            } else if (
                editProfileData.newPassword.length < 6
            ) {
                setFormErrors((p) => ({
                    ...p,
                    password: 'Password must be more than 6 characters',
                }));
            } else if (
                editProfileData.newPassword.length > 26
            ) {
                setFormErrors((p) => ({
                    ...p,
                    password: 'Password must not exceed 26 characters',
                }));
            }
        }
    };

    function checkEmail() {
        if (editProfileData == undefined) {
        } else {

            var re = /\S+@\S+\.\S+/;
            const validEmail = re.test(editProfileData.email);

            if (
                validEmail ||
                editProfileData.email == '' ||
                editProfileData.email == null
            ) {
                setFormErrors((p) => ({
                    ...p,
                    email: null,
                }));
            } else {
                setFormErrors((p) => ({
                    ...p,
                    email: 'Invalid email',
                }));
            }
        }
    };

    function refreshPage() {
        window.location.reload(false);
    }


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
                    disabled = {disableButton}
                    onClick = {updateUser} 
                    variant='outlined'
                    style={{ backgroundColor: '#6558f5', color: '#ffffff' }}
                >
                    Update Profile
                </Button>
            </Grid>
        </>
    );
}
