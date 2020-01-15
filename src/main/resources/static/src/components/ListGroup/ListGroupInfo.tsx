import * as React from "react";
import "./ListGroupInfo.scss";
import { IListGroupInfoProp, IListGroupInfoState } from "../../types/ListGroupTypes";
import { ListGroup, ListGroupItem } from 'reactstrap';

export default class ListGroupInfo extends React.Component<IListGroupInfoProp, IListGroupInfoState> {

    constructor(props: IListGroupInfoProp) {
        super(props);
        this.toggle = this.toggle.bind(this);
        this.selectedDropdownCallback = this.selectedDropdownCallback.bind(this);

        this.state = {
            dropdownOpen: false
        };

    }

    public componentDidMount(): void {

    }

    public componentDidUpdate(): void {
        
    }

    private selectedDropdownCallback(event: any): void {
        this.props.selectionCallback(event.target.dataset.station);
    }

    private outputInputItems(): JSX.Element[] {
        return this.props.input.map((row: any, index: number) => {
            return (
                <ListGroupItem 
                    key={row.name + index}
                    data-station={row.abbr}
                    onClick={this.selectedDropdownCallback}
                >{row.name}</ListGroupItem>
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
            <ListGroup>
                {(this.outputInputItems())}
            </ListGroup>
        )
    }

}
