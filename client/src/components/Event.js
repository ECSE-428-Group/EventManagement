import {
    Card,
    CardMedia,
    CardContent,
    Button,
    Typography
} from '@material-ui/core';

const Event = (props) => {
    return (
        <Card>
            <CardMedia
                component='img'
                image={props.image}
            />
            <CardContent>
                <Typography>{props.title}</Typography>
                <Typography>{props.text}</Typography>
                <Button color={"primary"}>Read More</Button>
            </CardContent>
        </Card>
    );
};

export default Event;
