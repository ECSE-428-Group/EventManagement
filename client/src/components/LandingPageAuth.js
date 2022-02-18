import React from 'react';

// Router
import { useNavigate } from 'react-router-dom';

// MUI
import Grid from '@material-ui/core/Grid';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import TextField from '@material-ui/core/TextField';

export default function LandingPageAuth({ input }) {
    const navigate = useNavigate();

    function handleClick() {
        navigate(`${input.page}`);
    }

    const textFields = input.data.map((data, idx) => {
        return (
            <Grid
                style={{
                    padding: '5px 0px',
                }}
            >
                <Typography gutterBottom variant='body2'>
                    {data}
                </Typography>
                <TextField
                    label={`${input.label[idx]}`}
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
        <Grid
            style={{ height: '100vh' }}
            container
            direction='row'
            justify='center'
            alignItems='center'
        >
            <Card style={{ boxShadow: 'none', border: '1px solid #c4c4c4' }}>
                <Grid
                    style={{ padding: 50 }}
                    container
                    direction='row'
                    justify='center'
                    alignItems='center'
                >
                    <Grid item xs={12}>
                        <Grid>
                            <Typography
                                variant='h5'
                                component='h1'
                                gutterBottom
                            >
                                {input.type}
                            </Typography>
                            <Typography variant='body2'>
                                Enter your details below to get started on
                                Joinit
                            </Typography>
                        </Grid>

                        <Grid style={{ padding: '10px 0px' }}>
                            {textFields}
                        </Grid>
                        <CardActions style={{ padding: '30px 0px 0px 0px' }}>
                            <Button
                                size='small'
                                fullWidth
                                style={{
                                    backgroundColor: '#6558f5',
                                    color: '#ffffff',
                                }}
                            >
                                {input.button}
                            </Button>
                        </CardActions>
                        <Typography
                            style={{
                                padding: '10px 0px',
                                textAlign: 'center',
                                fontSize: '10px',
                            }}
                        >
                            {input.footer}
                            <span
                                onClick={handleClick}
                                style={{
                                    color: '#6558f5',
                                    cursor: 'pointer',
                                }}
                            >
                                {' '}
                                {input.span}
                            </span>
                        </Typography>
                    </Grid>
                </Grid>
            </Card>
        </Grid>
    );
}