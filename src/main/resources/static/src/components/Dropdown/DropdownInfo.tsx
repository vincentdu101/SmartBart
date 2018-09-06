import * as React from "react";
import "./DropdownInfo.scss";
import { IDropdownInfoProp, IDropdownInfoState } from "../../types/DropdownTypes";
import { Dropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap';

export default class DropdownInfo extends React.Component<IDropdownInfoProp, IDropdownInfoState> {

    constructor(props: IDropdownInfoProp) {
        super(props);
        this.toggle = this.toggle.bind(this);

        this.state = {
            dropdownOpen: false
        };

    }

    public componentDidMount(): void {

    }

    public componentDidUpdate(): void {
        
    }

    private selectedDropdownCallback(event: any): void {
        this.props.selectionCallback(event.target.innerText);
    }

    private outputInputItems(): JSX.Element[] {
        return this.props.input.map((row: any, index: number) => {
            return (
                <DropdownItem 
                    key={row.name + index}
                    onClick={this.selectedDropdownCallback}
                >{row.name}</DropdownItem>
            );
        });
    }

    private toggle(): void {
        this.setState({
            dropdownOpen: !this.state.dropdownOpen
        });
    }

    public render() {
        return (
            <Dropdown isOpen={this.state.dropdownOpen} toggle={this.toggle}>
                <DropdownToggle caret={true}>
                    {this.props.label}
                </DropdownToggle>
                <DropdownMenu>
                    {this.outputInputItems()}  
                </DropdownMenu>  
            </Dropdown>
        )
    }

}
