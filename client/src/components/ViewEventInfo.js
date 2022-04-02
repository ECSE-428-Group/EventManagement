import React from 'react';
import { Link} from 'react-router-dom'

// MUI
import { Button, Grid, Typography } from '@material-ui/core';

export default function ViewEventInfo({eventDetails}) {

    const input = {
        title: 'What is this event about?',
        space: '  ',
    };

    //<><Typography style={styles.bold}>"I'm bold!" </Typography> </>
    //const styles = StyleSheet.create({
    //   bold: {fontWeight: 'bold'}
    //})

    return (
        <>
            <Grid
                container
                direction='row'
                justify='flex-start'
                alignItems='center'
                fontWeight='bold'
                style={{ margin: 30 }}
                item
                xs={10}
            >
                <Grid style={{padding: '5px 0px'}}>
                    <Typography variant='h7' component='h1'gutterBottom > {input.title} </Typography>
                </Grid>
            </Grid>
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
                    {input.space}
                </Grid>
            </Grid>
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
                    <Typography variant='h6' component='h4'gutterBottom > {eventDetails.description} </Typography>
                </Grid>
            </Grid>
            <Grid
                style={{ padding: '60px 10px 0px 0px' }}
                container
                justifyContent='flex-end'
                alignItems='flex-end'
            >
                <Button
                    component={Link} to="/userHome/"
                    variant='outlined'
                    style={{ backgroundColor: '#6558f5', color: '#ffffff' }}
                >
                    Back
                </Button>
            </Grid>
        </>
    );
}
