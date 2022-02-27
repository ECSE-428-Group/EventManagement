import React from 'react';

// MUI
import { Grid, TextField, Typography, Button } from '@material-ui/core';

export default function EditProfileUpdateInfo() {
    const input = {
        newPassword: 'Password',
        firstname: 'First Name',
        lastname: 'Last Name',
        email: 'Email'
    };

    const textLabels = ['New Password', 'First Name', 'Last Name', 'Email']

    const textFields = Object.values(input).map((data, idx) => {
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
                    //onClick = {updateUser} 
                    variant='outlined'
                    style={{ backgroundColor: '#6558f5', color: '#ffffff' }}
                >
                    Update Profile
                </Button>
            </Grid>
        </>
    );

    // function updateUser(username) {
    //     fetch('http://localhost:3000/$(username)'), {
    //         method: 'PUT',
    //         headers:{}
    //         body:JSON.strinify(input)
    //     }
    // }
}
