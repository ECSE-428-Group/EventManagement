import React, {useState, useEffect} from 'react';

// MUI
import { Grid, TextField, Typography, Button } from '@material-ui/core';

// Moment
import moment from 'moment';

export default function EditProfileUpdateInfo( {handleEditProfile} ) {

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
            'Enter your current password',
            'At least 6 characters',
            'Confirm your new password',
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
        curpassword: null,
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
            key={idx}
                style={{
                    padding: '5px 0px',
                }}
            >
                <Typography>{data}</Typography>
                <TextField
                    key={idx}
                    label={`${input.label[idx]}`}
                    error={
                        formErrors !== undefined &&
                        Object.values(formErrors)[idx] !== null
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
                    type={formType(idx)}
                />
            </Grid>
        );
    });

    function formType(idx) {
        if(idx === 0 || idx === 1 || idx === 2) {
            return 'password'
        }
        return 'text'
    }
    
    useEffect(() => {
        checkEmail();
        checkDate();
        checkPasswords();
        checkButton();
    },[confirmPassword,curPassword,newPassword,firstName,lastName,birthday,email]);

    function checkButton() {
        if ( // check if current password is empty
            curPassword === ""
        ||  // check if all other text fields are empty
            Object.values(textFields).every(value => {
                if (value === "" && value !== curPassword) {
                    return true;
                }
                return false;
            }) 
        || // check if any form errors are not null
            Object.values(formErrors).every(value => { 
                if (value !== null) {
                    return true;
                }
                return false
        })
        ) {
            setDisableButton(true);
        } else {
            setDisableButton(false);
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


        const status = handleEditProfile(editProfileData);
        if (status === 200) {
            refreshPage();
        }
    }

    function checkDate() {
        if (editProfileData === undefined) {
        } else {

            const validDate = moment(
                editProfileData.birthday,
                'YYYY-MM-DD',
                true
            ).isValid();

            if (
                validDate ||
                editProfileData.birthday === '' ||
                editProfileData.birthday === null
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
        if (editProfileData === undefined) {
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
                editProfileData.newPassword === undefined ||
                (editProfileData.newPassword.length >= 6 && editProfileData.newPassword.length <= 26) ||
                editProfileData.newPassword === ''
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
        if (editProfileData === undefined) {
        } else {

            var re = /\S+@\S+\.\S+/;
            const validEmail = re.test(editProfileData.email);

            if (
                validEmail ||
                editProfileData.email === '' ||
                editProfileData.email === null
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
                justifyContent='flex-start'
                alignItems='center'
                style={{ margin: 30 }}
                item
                xs={10}
            >
                <Grid item xs={10}>
                    {textFields}
                </Grid>
                <Grid>
                    <Button
                        disabled = {disableButton}
                        onClick = {updateUser} 
                        variant='outlined'
                        style={{ backgroundColor: '#6558f5', color: '#ffffff', marginTop: 30}}
                    >
                        Update Profile
                    </Button>
                </Grid>
            </Grid>
        </>
    );
}
