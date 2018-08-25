import * as React from "react";
import "./DropdownInfo.scss";
import { IDropdownInfoProp, IDropdownInfoState } from "../../types/DropdownTypes";
import { Dropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap';

export default class DropdownInfo extends React.Component<IDropdownInfoProp, IDropdownInfoState> {

    constructor(props: IDropdownInfoProp) {
        super(props);
        console.log(props);
        this.toggle = this.toggle.bind(this);

        this.state = {
            dropdownOpen: false
        };

    }

    public componentDidMount() {

    }

    private toggle() {
        this.setState({
            dropdownOpen: !this.state.dropdownOpen
        });
    }
    

    public render() {
        return (
            <Dropdown isOpen={this.state.dropdownOpen} toggle={this.toggle}>
                <DropdownToggle caret={true}>
                Dropdown
                </DropdownToggle>
                <DropdownMenu>
                <DropdownItem header={true}>Header</DropdownItem>
                <DropdownItem disabled={true}>Action</DropdownItem>
                <DropdownItem>Another Action</DropdownItem>
                <DropdownItem divider={true} />
                <DropdownItem>Another Action</DropdownItem>
                </DropdownMenu>
            </Dropdown>
        )
    }

}
