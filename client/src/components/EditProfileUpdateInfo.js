import React from 'react';

// MUI
import { Grid, TextField, Typography, Button } from '@material-ui/core';

export default function EditProfileUpdateInfo() {
    const input = {
        name: 'name',
        password: 'password',
        email: 'email',
        location: 'location',
    };

    const textFields = Object.keys(input).map((data, idx) => {
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
                    variant='outlined'
                    style={{ backgroundColor: '#6558f5', color: '#ffffff' }}
                >
                    Save Profile
                </Button>
            </Grid>
        </>
    );
}
