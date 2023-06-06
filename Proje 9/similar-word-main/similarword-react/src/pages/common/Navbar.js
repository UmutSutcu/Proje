import {AppBar, Button, Toolbar, Typography} from '@mui/material';
import '../../App.css'

function Navbar() {
    return (
        <AppBar className={"navbar"} position="static" >
            <Toolbar>
                <Typography variant="h4" component="div" sx={{ flexGrow: 1}}>
                    Common Text Generator
                </Typography>
              <Button  href={"/"} color={"inherit"}>Main</Button>
              <Button  href={"/contents"} color={"inherit"}>Contents</Button>
            </Toolbar>
        </AppBar>
    );
}
export default Navbar;