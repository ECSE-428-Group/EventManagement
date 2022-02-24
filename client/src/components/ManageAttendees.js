import React from 'react';

// MUI
import { Grid, Card, CardMedia, CardContent, Divider } from '@material-ui/core';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import TextField from '@material-ui/core/TextField';

export default function ManageAttendees() {
    return (
        <Grid
            style={{ height: '100vh' }}
            container
            direction='row'
            justify='center'
            alignItems='center'
        >
            <Grid item xs={3}>
                <Card style={{ backgroundColor: '#f8fcff', borderRadius: '0%',border: '1px solid #c4c4c4' }}>
                    <CardMedia
                        style={{
                            borderRadius: '0%',
                            objectFit: 'cover',
                            height: '200px',
                            width: '400px',
                        }}
                        component='img'
                        image={require('../static/images/chad.jpeg')}
                        alt='Chad'
                    />
                    <CardContent>
                        <Typography variant='body2'>
                                        Description of the event
                                    </Typography>
                    </CardContent>
                </Card>
            </Grid>

           
            <Grid item xs={9}>
                <Grid
                    style={{ height: '100vh' }}
                    container
                    columnSpacing={4}
                    direction='column'
                    justify='center'
                    alignItems='center'
                    >
                        
                        <Grid item xs={2}>
                            <Grid
                                style={{ height: '10vh' }}
                                container
                                direction='row'
                                justify='center'
                                alignItems='center'
                            >
                                <Grid item xs={6}>
                                    <TextField
                                        label={"Username"}
                                        variant='outlined'
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

                                <Grid item xs={3}>
                                    <Button>Remove</Button> 
                                </Grid>

                                <Grid item xs={3}>
                                    <Button>Remove All</Button>   
                                </Grid>
                            </Grid>
                        </Grid>

                        <Grid item xs={2}>
                            <Grid
                                style={{ height: '10vh' }}
                                container
                                direction='row'
                                justify='center'
                                alignItems='center'
                            >
                                <Grid item xs={6}>
                                <TextField
                                    label={"Username"}
                                    variant='outlined'
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

                                <Grid item xs={6}>
                                    <Button>Add</Button> 
                                </Grid>
                            </Grid>
                        </Grid>

                        <Grid item xs={2}>
                            <Grid
                                style={{ height: '10vh' }}
                                container
                                direction='row'
                                justify='center'
                                alignItems='center'
                            >
                                <Grid item xs={6}>
                                <TextField
                                    label={"Username"}
                                    variant='outlined'
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

                                <Grid item xs={3}>
                                    <Button>Get</Button> 
                                </Grid>

                                <Grid item xs={3}>
                                    <Button>Get All</Button>   
                                </Grid>
                            </Grid>
                        </Grid>

                        
                        
                </Grid>


            </Grid>

        </Grid>);
}